package com.seanshubin.game_4x.language

object NameAssembler : Assembler {
    override fun assemble(parts: List<Any>): String =
        (parts as List<Char>).joinToString("")
}
