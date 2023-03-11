package com.seanshubin.game_4x.language

object UnnamedAssembler : Assembler {
    override fun assemble(parts: List<Any>): Item {
        val attributes = parts[0] as List<Pair<String, Primitive>>
        val attributeMap = attributes.toMap()
        val item = Item(attributeMap)
        return item
    }
}
