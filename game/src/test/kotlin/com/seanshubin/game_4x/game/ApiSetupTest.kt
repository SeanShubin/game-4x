package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiSetupTest {
    @Test
    fun empty() {
        // given
        val api = createApi()
        val expected = emptyList<Planet>()

        // when
        val actual = api.listPlanets()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun createPlanet() {
        // given
        val api = createApi()
        val expected = listOf("Planet A")

        // when
        api.createPlanet("Planet A")

        // then
        val actual = api.listPlanets().map { it.name }
        assertEquals(expected, actual)
    }

    @Test
    fun planetsStartWithNoLand() {
        // given
        val api = createApi()
        api.createPlanet("Planet A")
        val expected = 0

        // when
        val actual = api.listLands("Planet A").size

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun createLand() {
        // given
        val api = createApi()
        api.createPlanet("Planet A")
        api.createLand("Planet A")
        val expected = 1

        // when
        val actual = api.listLands("Planet A").size

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun landStartsEmpty() {
        // given
        val api = createApi()
        val planetName = "Planet A"
        api.createPlanet(planetName)
        api.createLand(planetName)
        val landIndex = 0
        val expected = 0

        // when
        val actual = api.getLand(planetName, landIndex).size

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun createResource() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        val resource = Things.createNode(resource = "food", density = 6)

        // when
        api.addThing(planetName, landIndex, resource, 4)
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(4, actual.quantityByThing[resource])
    }

    @Test
    fun populateLand() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val api = createApi()
        val colonizer = Things.createColonizer()
        val unusedNode = Things.createNode(resource = "food", density = 6)
        val usedNode = Things.createNode(resource = "food", density = 6, activated = true)
        val gatherer = Things.createGatherer(resource = "food")
        api.createPlanet(planetName)
        api.createLand(planetName)
        api.addThing(planetName, landIndex, colonizer)
        api.addThing(planetName, landIndex, unusedNode, quantity = 4)
        api.addThing(planetName, landIndex, usedNode, quantity = 2)
        api.addThing(planetName, landIndex, gatherer, quantity = 5)

        // when
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(1, actual.quantityByThing.getValue(colonizer))
        assertEquals(4, actual.quantityByThing.getValue(unusedNode))
        assertEquals(2, actual.quantityByThing.getValue(usedNode))
        assertEquals(5, actual.quantityByThing.getValue(gatherer))
        assertEquals(12, actual.size)
    }

    private fun createApi(): Api = ApiImpl()
}
