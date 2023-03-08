package com.seanshubin.game_4x.language

object CallAssembler:Assembler {
    override fun assemble(parts: List<Any>): Call =
        Call(
            parts[0] as String,
            parts[1] as List<Value>
        )
}
