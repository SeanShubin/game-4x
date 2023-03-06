package com.seanshubin.game_4x.prototype3

import com.seanshubin.game_4x.prototype3.PrimitiveUtil.toPrimitive

object Format {
    private val name = """\w+"""
    private val value = """[\w$]+"""
    private val attribute = """$name=$value"""
    private val attributeRegex = Regex("""($name)=($value)""")
    private val namedItemRegex = Regex("""\{($name)((?: $attribute)*)}""")
    private val unNamedItemRegex = Regex("""\{($attribute(?: $attribute)*)}""")
    fun parseItem(text:String):Item? =
        parseNamedItem(text) ?: parseUnnamedItem(text) ?: parseEmptyItem(text)

    private fun parseEmptyItem(text:String):Item? =
        if(text == "{}") Item() else null

    private fun parseNamedItem(text:String):Item? {
        val matchResult = namedItemRegex.matchEntire(text) ?: return null
        val name = matchResult.groupValues[1]
        val attributeListString = matchResult.groupValues[2]
        val attributeList = parseAttributeList(attributeListString)
        return Item(mapOf("name" to name.toPrimitive()) + attributeList.toMap())
    }

    private fun parseUnnamedItem(text:String):Item? {
        val matchResult = unNamedItemRegex.matchEntire(text) ?: return null
        val attributeListString = matchResult.groupValues[1]
        val attributeList = parseAttributeList(attributeListString)
        return Item(attributeList.toMap())
    }

    private fun parseAttributeList(text:String):List<Pair<String, PrimitiveValue>> {
        val matchResults = attributeRegex.findAll(text).toList()
        return matchResults.map { matchResult ->
            val name = matchResult.groupValues[1]
            val value = matchResult.groupValues[2]
            name to value.toPrimitive()
        }
    }
}
