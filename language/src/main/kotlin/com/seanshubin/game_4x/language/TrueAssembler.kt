package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.PBoolean.Companion.toPBoolean

object TrueAssembler:Assembler {
    override fun assemble(parts: List<Any>): PBoolean = true.toPBoolean()
}