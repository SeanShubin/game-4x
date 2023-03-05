package com.seanshubin.game_4x.prototype2

import org.junit.Test
import kotlin.math.exp
import kotlin.test.assertEquals

class InterpreterTest {
    @Test
    fun add(){
        // given
        val expectedState = Items(mapOf(
            Item(mapOf(
                "name" to "citizen",
                "activated" to false
            )) to 1
        ))
        val expectedMessages = listOf("0 -> 1 changed quantity of {citizen activated=false}")
        val expected = Result.success(expectedState, expectedMessages)
        val commandFactory:CommandFactory = CommandFactoryImpl()
        val interpreter:Interpreter = InterpreterImpl(commandFactory)
        val initialState = Items()

        // when
        val actual = interpreter.execute(initialState,"add {citizen activated=false}")

        // then
        assertEquals(expected, actual)
    }
    @Test
    fun remove(){
        // given
        val initialState = Items(mapOf(
            Item(mapOf(
                "name" to "citizen",
                "activated" to false
            )) to 1
        ))
        val expectedState = Items()
        val expectedMessages = listOf("1 -> 0 changed quantity of {citizen activated=false}")
        val expected = Result.success(expectedState, expectedMessages)
        val commandFactory:CommandFactory = CommandFactoryImpl()
        val interpreter:Interpreter = InterpreterImpl(commandFactory)

        // when
        val actual = interpreter.execute(initialState, "remove {citizen activated=false}")

        // then
        assertEquals(expected, actual)
    }
}
