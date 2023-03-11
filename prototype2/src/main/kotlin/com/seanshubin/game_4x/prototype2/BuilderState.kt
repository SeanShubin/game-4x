package com.seanshubin.game_4x.prototype2

interface BuilderState {
    fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder>

    companion object {
        object ReadingHeader : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingSubCommands, builder)
                }
                val inputName = Format.parseInputLine(line.text)
                if (inputName != null) {
                    return Pair(ReadingHeader, builder.input(inputName))
                }
                val requiredTopCommand = Format.parseRequiredLine(line.text)
                if (requiredTopCommand != null) {
                    return Pair(ReadingHeader, builder.topCommand(requiredTopCommand))
                }
                val optionalTopCommand = Format.parseOptionalLine(line.text)
                if (optionalTopCommand != null) {
                    return Pair(ReadingHeader, builder.topCommand(optionalTopCommand))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingSubCommands : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                val subCommandName = Format.parseSubName(line.text)
                if (line.text == "example") {
                    return Pair(ReadingExampleContents, builder)
                }
                if (line.text.isBlank()) {
                    return Pair(Failure, builder)
                }
                if (subCommandName != null) {
                    return Pair(ReadingSubCommandCalls, builder.subCommandName(subCommandName))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingSubCommandCalls : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.source == "src/main/resources/com/seanshubin/game_4x/prototype2/reset-all.txt" && line.index == 3) {
                    println("found it")
                }
                if (line.text.isBlank()) {
                    return Pair(ReadingSubCommands, builder.subCommandFinalize())
                }
                val call = Format.parseCall(line.text)
                if (call != null) {
                    return Pair(ReadingSubCommandCalls, builder.subCommandCall(call))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExamples : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingExamples, builder)
                }
                if (line.text == "example") {
                    return Pair(ReadingExampleContents, builder)
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExampleContents : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection == null) {
                    throw RuntimeException("expected example section, got '$line'")
                } else {
                    return Pair(newExampleSection, builder)
                }
            }
        }

        object ReadingExampleParameters : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingExamples, builder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection != null) {
                    return Pair(newExampleSection, builder)
                }
                val parameter = Format.parseParameter(line.text.trim())
                if (parameter != null) {
                    return Pair(this, builder.exampleParameter(parameter))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExampleBuilderStateBefore : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingExamples, builder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection != null) {
                    return Pair(newExampleSection, builder)
                }
                val item = Format.parseItem(line.text.trim())
                if (item != null) {
                    return Pair(this, builder.exampleStateBeforeItem(item))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExampleLog : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingExamples, builder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection != null) {
                    return Pair(newExampleSection, builder)
                }
                return Pair(this, builder.exampleLog(line.text.trim()))
            }
        }

        object ReadingExampleBuilderStateAfter : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingExamples, builder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection != null) {
                    return Pair(newExampleSection, builder)
                }
                val item = Format.parseItem(line.text.trim())
                if (item != null) {
                    return Pair(this, builder.exampleStateAfterItem(item))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExample : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                throw UnsupportedOperationException("not implemented")
            }
        }

        object Failure : BuilderState {
            override fun processLine(builder: Builder, line: Line): Pair<BuilderState, Builder> {
                throw RuntimeException("reached failure state with '$line'")
            }
        }

        fun checkForExampleSection(line: Line): BuilderState? =
            when (line.text.trim()) {
                "parameters" -> ReadingExampleParameters
                "state-before" -> ReadingExampleBuilderStateBefore
                "log" -> ReadingExampleLog
                "state-after" -> ReadingExampleBuilderStateAfter
                else -> null
            }
    }
}
