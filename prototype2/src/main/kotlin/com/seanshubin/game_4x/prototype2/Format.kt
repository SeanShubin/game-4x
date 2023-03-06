package com.seanshubin.game_4x.prototype2

object Format {
    val lineBreak = Regex("\r\n|\r|\n")
    private const val name = """\$?[\w-]+"""
    private const val nameCapture = """($name)"""
    private const val namedItemSubstitute = """\{$name(?: $name=$name)*}"""
    private const val namedItemCapture = """\{$nameCapture((?: $name=$name)*)}"""
    private const val unnamedItemSubstitute = """\{(?:\s*$name=$name\s*)*}"""
    private const val unnamedItemCapture = """\{((?:\s*$name=$name\s*)*)}"""
    private const val itemCapture = """$namedItemCapture|$unnamedItemCapture"""
    private const val itemSubstitute = """$namedItemSubstitute|$unnamedItemSubstitute"""
    private const val valueSubstitute = """$name|$namedItemSubstitute"""
    private const val valueCapture = """($valueSubstitute)"""
    private const val input = """input ($name)"""
    private const val required = """required ($name)"""
    private const val optional = """optional ($name)"""
    private const val call = """$nameCapture(\s*$itemSubstitute)*"""
    private const val parameter = """$nameCapture=$valueCapture"""
    private val inputRegex = Regex(input)
    private val requiredRegex = Regex(required)
    private val optionalRegex = Regex(optional)
    private val subNameRegex = Regex(nameCapture)
    private val callRegex = Regex(call)
    private val itemRegex = Regex(itemCapture)
    private val namedItemRegex = Regex(namedItemCapture)
    private val unnamedItemRegex = Regex(unnamedItemCapture)
    private val parameterRegex = Regex(parameter)

    fun formatItem(item: Item): String = item.attributeMap.map { (name, value) ->
        if (name == "name") {
            "$value"
        } else {
            "$name=$value"
        }
    }.joinToString(" ", "{", "}")

    fun formatItems(items: Items): String = items.itemMap.map { (item, quantity) ->
        "$quantity of ${formatItem(item)}"
    }.joinToString(" ", "{", "}")

    fun parseInputLine(line: String): String? {
        val matchResult = inputRegex.matchEntire(line) ?: return null
        return matchResult.groupValues[1]
    }

    fun parseRequiredLine(line: String): TopCommand? {
        val matchResult = requiredRegex.matchEntire(line) ?: return null
        return TopCommand(required = true, matchResult.groupValues[1])
    }

    fun parseOptionalLine(line: String): TopCommand? {
        val matchResult = optionalRegex.matchEntire(line) ?: return null
        return TopCommand(required = false, matchResult.groupValues[1])
    }

    fun parseSubName(line: String): String? {
        val matchResult = subNameRegex.matchEntire(line) ?: return null
        return matchResult.groupValues[1]
    }

    fun parseCall(line: String): SubCommandCall? {
        val matchResult = callRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val parametersString = matchResult.groupValues[2]
        val parameters = parseItemList(parametersString)
        return SubCommandCall(name, parameters)
    }

    fun parseItemList(line: String): List<Item> {
        val matches = itemRegex.findAll(line).toList()
        return matches.mapNotNull {
            parseItem(it.groupValues[0])
        }.toList()
    }

    fun parseItem(line: String): Item? = parseNamedItem(line) ?: parseUnnamedItem(line)

    private fun parseNamedItem(line: String): Item? {
        val matchResult = namedItemRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val remain = matchResult.groupValues[2]
        val parameters = parseParameterList(remain)
        return Item(mapOf("name" to name) + parameters.toMap())
    }

    private fun parseUnnamedItem(line: String): Item? {
        val matchResult = unnamedItemRegex.matchEntire(line) ?: return null
        val attributesString = matchResult.groupValues[1]
        val attributeList = parseAttributeList(attributesString)
        return Item(attributeList.toMap())
    }

    fun parseParameter(line: String): Pair<String, Any>? {
        val matchResult = parameterRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val value = matchResult.groupValues[2].toDynamic()
        return Pair(name, value)
    }

    fun parseParameterList(line: String): List<Pair<String, Any>> {
        val matchResults = parameterRegex.findAll(line)
        return matchResults.map {
            val name = it.groupValues[1]
            val value = it.groupValues[2].toDynamic()
            Pair(name, value)
        }.toList()
    }

    fun parseAttributeList(line: String): List<Pair<String, Any>> {
        val matchResults = parameterRegex.findAll(line)
        return matchResults.map {
            val name = it.groupValues[1]
            val value = it.groupValues[2].toDynamic()
            Pair(name, value)
        }.toList()
    }

    private fun String.toDynamic(): Any = toIntOrNull() ?: toBooleanStrictOrNull() ?: this

}