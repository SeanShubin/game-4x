package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.PString.Companion.toPString

object NamedAssembler : Assembler {
    override fun assemble(parts: List<Any>): Item {
        val name = parts[0] as String
        val otherAttributes = parts[1] as List<Pair<String, Primitive>>
        val attributes = listOf(Pair("name", name.toPString())) + otherAttributes
        val attributeMap = attributes.toMap()
        val item = Item(attributeMap)
        return item
    }
}
