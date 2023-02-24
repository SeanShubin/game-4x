package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiTest {
    @Test
    fun empty(){
        // given
        val api = createApi()
        val expected = emptyList<String>()

        // when
        val actual = api.listPlanets()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun addPlanet(){
        // given
        val api = createApi()
        val expected = listOf("Planet A")

        // when
        api.createPlanet("Planet A")

        // then
        val actual = api.listPlanets()
        assertEquals(expected, actual)
    }

    private fun createApi():Api = ApiImpl()
}