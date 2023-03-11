package com.seanshubin.game_4x.prototype2

import kotlin.test.Test
import kotlin.test.assertEquals

class ItemsTest {
    @Test
    fun addOne() {
        // given
        val input = Items()
        val expected = Items(mapOf(Item(mapOf("name" to "citizen")) to 1))

        // when
        val actual = input.add(Item(mapOf("name" to "citizen")))

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun addSecond() {
        // given
        val input = Items(mapOf(Item(mapOf("name" to "citizen")) to 1))
        val expected = Items(mapOf(Item(mapOf("name" to "citizen")) to 2))

        // when
        val actual = input.add(Item(mapOf("name" to "citizen")))

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun addDifferent() {
        // given
        val input = Items(mapOf(Item(mapOf("name" to "citizen")) to 1))
        val expected = Items(
            mapOf(
                Item(mapOf("name" to "citizen")) to 1,
                Item(mapOf("name" to "colonizer")) to 1
            )
        )

        // when
        val actual = input.add(Item(mapOf("name" to "colonizer")))

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun removeOnly() {
        // given
        val input = Items(mapOf(Item(mapOf("name" to "citizen")) to 1))
        val expected = Items()

        // when
        val actual = input.removeOrNull(Item(mapOf("name" to "citizen")))

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun removeSecond() {
        // given
        val input = Items(mapOf(Item(mapOf("name" to "citizen")) to 2))
        val expected = Items(mapOf(Item(mapOf("name" to "citizen")) to 1))

        // when
        val actual = input.removeOrNull(Item(mapOf("name" to "citizen")))

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun removeNone() {
        // given
        val input = Items()
        val expected: Items? = null

        // when
        val actual = input.removeOrNull(Item(mapOf("name" to "citizen")))

        // then
        assertEquals(expected, actual)
    }
}
