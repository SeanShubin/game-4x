package com.seanshubin.game_4x.prototype4.assembler

object AssemblerUtil {
    val nullAssembler: (parts: List<Any>) -> Nothing? = { null }
    val listAssembler: (parts: List<Any>) -> List<Any> = { it }
    val delegateToNestedAssembler: (parts: List<Any>) -> Any = { it[0] }
    val trueAssembler: (parts: List<Any>) -> Boolean = { true }
    val falseAssembler: (parts: List<Any>) -> Boolean = { false }
}
