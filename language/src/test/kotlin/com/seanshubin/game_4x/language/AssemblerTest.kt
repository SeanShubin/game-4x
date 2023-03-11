package com.seanshubin.game_4x.language

import kotlin.test.Test
import kotlin.test.assertTrue

class AssemblerTest {
    @Test
    fun call() {
        val text = "foo 3 {name resource=food active=true} {active=false}"
        val iterator = text.iterator()
        val cursor = IteratorCursor(iterator)
        val result = Expressions.call.consume(cursor) as Result.Success
        val remain = result.cursor.reify().joinToString("")
        assertTrue(result.cursor.isEnd, "text remains after parse: '$remain'")
        val parseTree = result.tree
        val assembler = AssemblerFromTreeImpl(Assemblers.assemblerMap)
        val call = assembler.assemble(parseTree) as Call
        println(call)
        parseTree.toLines().forEach(::println)
    }
}