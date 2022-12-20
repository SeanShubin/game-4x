package com.seanshubin.game_4x.game

import org.junit.Test

class SampleGameTest {
    @Test
    fun colonize(){
        val gameState = GameState.empty
        val api: Api = ApiImpl(gameState)
        api.addPlanet("Planet A", size = 10)
        val planetA = api.planet("Planet A")
        api.addPlanet("Planet B", size = 6)
        planetA.addResource(Resource("food", quantity = 4, rate = 4))
        planetA.addResource(Resource("material", quantity = 2, rate = 7))
        planetA.addResource(Resource("energy", quantity = 6, rate = 5))
        val planetB = api.planet("Planet B")
        planetB.addResource(Resource("food", quantity = 2, rate = 3))
        planetB.addResource(Resource("material", quantity = 8, rate = 6))
        planetB.addResource(Resource("energy", quantity = 3, rate = 4))
        planetA.addOrbital("colonizer")

        val gameStateJson = JsonMappers.pretty.writeValueAsString(api.gameState())
        println(gameStateJson)
    }
}