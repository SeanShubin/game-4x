package com.seanshubin.game_4x.language

object PairAssembler : Assembler {
    override fun assemble(parts: List<Any>): Pair<String, Primitive> =
        Pair(parts[0] as String, parts[1] as Primitive)

}
