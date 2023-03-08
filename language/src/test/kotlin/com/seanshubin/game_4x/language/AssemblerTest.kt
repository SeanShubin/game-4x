package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Assemblers.assemble
import com.seanshubin.game_4x.language.Assemblers.assembleCall
import org.junit.Test
import kotlin.test.assertEquals

class AssemblerTest {
    @Test
    fun call(){
        val text = "foo 3 {name resource=food} {active=false}"
        val iterator = text.iterator()
        val cursor = IteratorCursor(iterator)
        val result = Expressions.call.consume(cursor) as Result.Success
        val parseTree = result.tree
        val call = assemble(parseTree) as Call
        println(call)
        parseTree.toLines().forEach(::println)
    }
}