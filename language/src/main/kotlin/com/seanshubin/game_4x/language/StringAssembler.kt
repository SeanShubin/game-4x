package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.PString.Companion.toPString

object StringAssembler:Assembler {
    override fun assemble(parts: List<Any>): PString =
        (parts as List<Char>).joinToString("").toPString()
}
