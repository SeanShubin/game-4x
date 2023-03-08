package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.PNumber.Companion.toPNumber

object NumberAssembler:Assembler {
    override fun assemble(parts: List<Any>): PNumber =
        (parts as List<Char>).joinToString("").toInt().toPNumber()
}