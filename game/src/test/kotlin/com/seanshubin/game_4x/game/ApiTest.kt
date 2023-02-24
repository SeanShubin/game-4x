package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiTest {
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
        val actual = api.listPlanets().map{it.name}
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
    fun landStartsWithNoResources() {
        // given
        val api = createApi()
        val planetName = "Planet A"
        api.createPlanet(planetName)
        api.createLand(planetName)
        val landIndex = 0
        val expected = 0

        // when
        val actual = api.listResources(planetName, landIndex).size

        // then
        assertEquals(expected, actual)
    }

    private fun createApi(): Api = ApiImpl()
}
