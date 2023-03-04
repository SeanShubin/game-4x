package com.seanshubin.game_4x.prototype2

import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
    @Test
    fun parseInput() {
        // given
        val input = "input resource"
        val expected = "resource"

        // when
        val actual = Parser.parseInputLine(input)

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun parseRequired() {
        // given
        val input = "required activate-node"
        val expected = TopCommand("required", "activate-node")

        // when
        val actual = Parser.parseRequiredLine(input)

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun parseOptional() {
        // given
        val input = "optional add-supply"
        val expected = TopCommand("optional", "add-supply")

        // when
        val actual = Parser.parseOptionalLine(input)

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun parseSubName() {
        // given
        val input = "activate-node"
        val expected = "activate-node"

        // when
        val actual = Parser.parseSubName(input)

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun parseSubCommandCall() {
        // given
        val input = "activate {node resource=\$resource density=\$density activated=false}"
        val name = "activate"
        val item = Item(
            mapOf(
                "name" to "node",
                "resource" to "\$resource",
                "density" to "\$density",
                "activated" to false
            )
        )
        val calls = listOf(item)
        val expected = SubCommandCall(name, calls)

        // when
        val actual = Parser.parseCall(input)
        assertEquals(expected, actual)
    }

    @Test
    fun parseItem() {
        // given
        val input = "{node resource=\$resource density=\$density activated=false}"
        val expected = Item(
            mapOf(
                "name" to "node",
                "resource" to "\$resource",
                "density" to "\$density",
                "activated" to false
            )
        )

        // when
        val actual = Parser.parseItem(input)
        assertEquals(expected, actual)
    }

    @Test
    fun parseItemList() {
        // given
        val input = " {node resource=\$resource density=\$density activated=false}"
        val item = Item(
            mapOf(
                "name" to "node",
                "resource" to "\$resource",
                "density" to "\$density",
                "activated" to false
            )
        )
        val expected = listOf(item)

        // when
        val actual = Parser.parseItemList(input)
        assertEquals(expected, actual)
    }
}