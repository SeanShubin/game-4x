package com.seanshubin.game_4x.language

import org.junit.Test

class ParserTest {
    @Test
    fun parseCall(){
        val text = "call 3 {name} {key=value}"
        val iterator = text.iterator()
        val cursor = IteratorCursor(iterator)
        val result = Expressions.call.consume(cursor)
        result.tree.toLines().forEach(::println)
    }
}