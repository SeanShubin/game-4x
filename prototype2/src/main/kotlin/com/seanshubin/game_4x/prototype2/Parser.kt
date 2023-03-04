package com.seanshubin.game_4x.prototype2

object Parser {
    val lineBreak = Regex("\r\n|\r|\n")
    private const val nameSubstitute = """\$?[\w-]+"""
    private const val nameCapture = """($nameSubstitute)"""
    private const val item = """\{$nameCapture((?: $nameSubstitute=$nameSubstitute)*)}"""
    private const val input = """input ($nameSubstitute)"""
    private const val required = """required ($nameSubstitute)"""
    private const val optional = """optional ($nameSubstitute)"""
    private const val call = """$nameCapture( $item)*"""
    private const val parameter = """$nameCapture=$nameCapture"""
    private val inputRegex = Regex(input)
    private val requiredRegex = Regex(required)
    private val optionalRegex = Regex(optional)
    private val subNameRegex = Regex(nameCapture)
    private val callRegex = Regex(call)
    private val itemRegex = Regex(item)
    private val parameterRegex = Regex(parameter)
    fun parseInputLine(line: String):String? {
        val matchResult = inputRegex.matchEntire(line) ?: return null
        return matchResult.groupValues[1]
    }
    fun parseRequiredLine(line: String):TopCommand? {
        val matchResult = requiredRegex.matchEntire(line) ?: return null
        return TopCommand("required", matchResult.groupValues[1])
    }
    fun parseOptionalLine(line: String):TopCommand? {
        val matchResult = optionalRegex.matchEntire(line) ?: return null
        return TopCommand("optional", matchResult.groupValues[1])
    }
    fun parseSubName(line:String):String?{
        val matchResult = subNameRegex.matchEntire(line) ?: return null
        return matchResult.groupValues[1]
    }
    fun parseCall(line:String):SubCommandCall? {
        val matchResult = callRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val parametersString = matchResult.groupValues[2]
        val parameters = parseItemList(parametersString)
        return SubCommandCall(name, parameters)
    }
    fun parseItemList(line:String):List<Item> {
        val matches = itemRegex.findAll(line).toList()
        return matches.mapNotNull{
            parseItem(it.groupValues[0])
        }.toList()
    }
    fun parseItem(line:String):Item? {
        val matchResult = itemRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val remain = matchResult.groupValues[2]
        val parameters = parseParameterList(remain)
        return Item(mapOf("name" to name) + parameters.toMap())
    }

    fun parseParameter(line:String):Pair<String, Any>? {
        val matchResult = parameterRegex.matchEntire(line) ?: return null
        val name = matchResult.groupValues[1]
        val value = matchResult.groupValues[2].toDynamic()
        return Pair(name, value)
    }

    fun parseParameterList(line:String):List<Pair<String, Any>>{
        val matchResults = parameterRegex.findAll(line)
        return matchResults.map {
            val name = it.groupValues[1]
            val value = it.groupValues[2].toDynamic()
            Pair(name, value)
        }.toList()
    }

    private fun String.toDynamic():Any =
        toIntOrNull() ?: toBooleanStrictOrNull() ?: this

}