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
    fun landStartsEmpty() {
        // given
        val api = createApi()
        val planetName = "Planet A"
        api.createPlanet(planetName)
        api.createLand(planetName)
        val landIndex = 0
        val expected = 0

        // when
        val actual = api.listThings(planetName, landIndex).size

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun createResource() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val name = "food"
        val prevalence = 4
        val density = 6
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        val resource = Resource("food", density=6)
        val expected = listOf(resource, resource, resource, resource)

        // when
        api.createResource(planetName, landIndex, name, prevalence, density)
        val actual = api.listThings(planetName, landIndex)

        // then
        assertEquals(expected, actual)
    }

    private fun createApi(): Api = ApiImpl()
}
