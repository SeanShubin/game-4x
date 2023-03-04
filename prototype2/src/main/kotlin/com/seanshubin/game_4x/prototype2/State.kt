package com.seanshubin.game_4x.prototype2

interface State {
    fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder>

    companion object {
        object ReadingHeader : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingSubCommands, commandScriptBuilder)
                }
                val inputName = Parser.parseInputLine(line.text)
                if (inputName != null) {
                    return Pair(ReadingHeader, commandScriptBuilder.input(inputName))
                }
                val requiredTopCommand = Parser.parseRequiredLine(line.text)
                if (requiredTopCommand != null) {
                    return Pair(ReadingHeader, commandScriptBuilder.topCommand(requiredTopCommand))
                }
                val optionalTopCommand = Parser.parseOptionalLine(line.text)
                if (optionalTopCommand != null) {
                    return Pair(ReadingHeader, commandScriptBuilder.topCommand(optionalTopCommand))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingSubCommands : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                val subCommandName = Parser.parseSubName(line.text)
                if (line.text == "example") {
                    return Pair(ReadingExampleContents, commandScriptBuilder)
                }
                if (line.text.isBlank()) {
                    return Pair(Failure, commandScriptBuilder)
                }
                if (subCommandName != null) {
                    return Pair(ReadingSubCommandCalls, commandScriptBuilder.subCommandName(subCommandName))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingSubCommandCalls : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if (line.text.isBlank()) {
                    return Pair(ReadingSubCommands, commandScriptBuilder.subCommandFinalize())
                }
                val call = Parser.parseCall(line.text)
                if (call != null) {
                    return Pair(ReadingSubCommandCalls, commandScriptBuilder.subCommandCall(call))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExamples : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if(line.text.isBlank()){
                    return Pair(ReadingExamples, commandScriptBuilder)
                }
                if (line.text == "example") {
                    return Pair(ReadingExampleContents, commandScriptBuilder)
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }
        object ReadingExampleContents : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                val newExampleSection = checkForExampleSection(line)
                if (newExampleSection == null) {
                    throw RuntimeException("expected example section, got '$line'")
                } else {
                    return Pair(newExampleSection, commandScriptBuilder)
                }
            }
        }

        object ReadingExampleParameters : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if(line.text.isBlank()){
                    return Pair(ReadingExamples, commandScriptBuilder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if(newExampleSection != null){
                    return Pair(newExampleSection, commandScriptBuilder)
                }
                val parameter = Parser.parseParameter(line.text.trim())
                if(parameter != null){
                    return Pair(this, commandScriptBuilder.exampleParameter(parameter))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExampleStateBefore : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if(line.text.isBlank()){
                    return Pair(ReadingExamples, commandScriptBuilder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if(newExampleSection != null){
                    return Pair(newExampleSection, commandScriptBuilder)
                }
                val item = Parser.parseItem(line.text.trim())
                if(item != null){
                    return Pair(this, commandScriptBuilder.exampleStateBeforeItem(item))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExampleLog : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if(line.text.isBlank()){
                    return Pair(ReadingExamples, commandScriptBuilder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if(newExampleSection != null){
                    return Pair(newExampleSection, commandScriptBuilder)
                }
                return Pair(this, commandScriptBuilder.exampleLog(line.text.trim()))
            }
        }

        object ReadingExampleStateAfter : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                if(line.text.isBlank()){
                    return Pair(ReadingExamples, commandScriptBuilder.exampleFinalize())
                }
                val newExampleSection = checkForExampleSection(line)
                if(newExampleSection != null){
                    return Pair(newExampleSection, commandScriptBuilder)
                }
                val item = Parser.parseItem(line.text.trim())
                if(item != null){
                    return Pair(this, commandScriptBuilder.exampleStateAfterItem(item))
                }
                throw RuntimeException("line '$line' did not match a valid pattern for state ${javaClass.simpleName}")
            }
        }

        object ReadingExample : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                throw UnsupportedOperationException("not implemented")
            }
        }

        object Failure : State {
            override fun processLine(commandScriptBuilder: CommandScriptBuilder, line: Line): Pair<State, CommandScriptBuilder> {
                throw RuntimeException("reached failure state with '$line'")
            }
        }

        fun checkForExampleSection(line:Line):State? =
            when (line.text.trim()) {
                "parameters" -> ReadingExampleParameters
                "state-before" -> ReadingExampleStateBefore
                "log" -> ReadingExampleLog
                "state-after" -> ReadingExampleStateAfter
                else -> null
            }
    }
}
