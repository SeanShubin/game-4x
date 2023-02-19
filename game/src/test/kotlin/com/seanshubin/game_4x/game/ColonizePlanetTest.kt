package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ColonizePlanetTest {
    @Test
    fun typicalColonizeScenario(){
        // given
        val emptyFoodResource = Resource(name=Names.FOOD, current = 0, maximum = 4, rate = 10)
        val singleFoodResource = emptyFoodResource.buildGatherer()!!
        val unclaimedLand = Land.empty.addResource(emptyFoodResource)
        val claimedLand = Land.empty.addResource(singleFoodResource).copy(claimed = true)
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(unclaimedLand)
        val expected = Planet("the planet").addLand(claimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun willNotColonizeWithoutColonizer(){
        // given
        val foodResource = Resource(name=Names.FOOD, current = 0, maximum = 4, rate = 10)
        val unclaimedLand = Land.empty.addResource(foodResource)
        val planet = Planet("the planet").addLand(unclaimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull(actual)
    }

    @Test
    fun willNotColonizeIfColonyExists(){
        // given
        val foodResource = Resource(name=Names.FOOD, current = 0, maximum = 4, rate = 10)
        val claimedLand = Land.empty.addResource(foodResource).copy(claimed = true)
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(claimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull( actual)
    }

    @Test
    fun willNotColonizeIfNoFood(){
        // given
        val unclaimedLand = Land.empty
        val planet = Planet("the planet").addOrbital(Names.COLONIZER).addLand(unclaimedLand)

        // when
        val actual = planet.colonize()

        // then
        assertNull(actual)
    }
}
