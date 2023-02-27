package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiGameplayTest {
    @Test
    fun startWithColonizer() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        api.addThing(planetName, landIndex, node)
        api.addThing(planetName, landIndex, colonizer)
        val expected = Land(
            node to 1,
            colonizer to 1
        )

        // when
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(
            expected.toLines().joinToString("\n"),
            actual.toLines().joinToString("\n")
        )

    }

    @Test
    fun colonize() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)
        val citizen = Things.createCitizen()
        val gatherer = Things.createGatherer("food")
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        api.addThing(planetName, landIndex, node)
        api.addThing(planetName, landIndex, colonizer)
        val expected = Land(
            citizen to 1,
            gatherer to 1
        )

        // when
        api.colonize(planetName, landIndex, "colonize")
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(expected, actual)
    }

    private fun createApi(): Api = ApiImpl()
}
