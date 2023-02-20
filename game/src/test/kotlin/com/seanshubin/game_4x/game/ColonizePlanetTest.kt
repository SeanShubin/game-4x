package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ColonizePlanetTest {
    @Test
    fun typicalColonizeScenario() {
        // given
        val emptyFoodResource = Resource(
            name = Names.FOOD,
            density = 10,
            inGround = 4
        )
        val singleFoodResource = emptyFoodResource.buildGatherer()!!
        val unclaimedLand = Land(resources = Resources(listOf(emptyFoodResource)))
        val claimedLand = Land(resources = Resources(listOf(singleFoodResource))).copy(claimed = true)
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(unclaimedLand)
        val expected = Planet("the planet").addLand(claimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun willNotColonizeWithoutColonizer() {
        // given
        val foodResource = Resource(
            name = Names.FOOD,
            density = 10,
            inGround = 4
        )
        val unclaimedLand = Land(resources = Resources(listOf(foodResource)))
        val planet = Planet("the planet").addLand(unclaimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull(actual)
    }

    @Test
    fun willNotColonizeIfColonyExists() {
        // given
        val foodResource = Resource(
            name = Names.FOOD,
            density = 10,
            inGround = 4
        )
        val claimedLand = Land(resources = Resources(listOf(foodResource))).copy(claimed = true)
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(claimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull(actual)
    }

    @Test
    fun willNotColonizeIfNoFood() {
        // given
        val unclaimedLand = Land()
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(unclaimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull(actual)
    }
}
