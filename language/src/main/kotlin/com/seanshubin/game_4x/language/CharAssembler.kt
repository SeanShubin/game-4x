package com.seanshubin.game_4x.language

object CharAssembler:Assembler {
    override fun assemble(parts: List<Any>): Char =
        (parts as List<Char>)[0]
}
