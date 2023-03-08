package com.seanshubin.game_4x.language

object DelegateToNestedAssembler:Assembler {
    override fun assemble(parts: List<Any>): Any? =
        if(parts.size == 1){
            parts[0]
        } else {
            throw RuntimeException("expected exactly 1 nested value")
        }
}