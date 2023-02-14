package com.seanshubin.game_4x.game

import org.junit.Test

class SampleGameTest {
    @Test
    fun colonize(){
        val gameState = GameState.empty
        val api: Api = ApiImpl(gameState)
        val planetResourcesA = Resources(listOf(
            Resource("food", quantity = 4, rate = 4),
            Resource("material", quantity = 2, rate = 7),
            Resource("energy", quantity = 6, rate = 5)
        ))
        api.addPlanet("Planet A", size = 10, planetResourcesA)
        val planetA = api.planet("Planet A")
        val planetResourcesB = Resources(listOf(
            Resource("food", quantity = 2, rate = 3),
            Resource("material", quantity = 8, rate = 6),
            Resource("energy", quantity = 3, rate = 4)
        ))
        api.addPlanet("Planet B", size = 6, planetResourcesB)
        planetA.addOrbital("colonizer")
        val strategy:Strategy = StrategyImpl(api)
        val gameBehavior = GameBehaviorImpl(strategy)
        val previousGames = emptyList<GameState>()
        val currentGame = api.gameState()
        val finalGame = gameBehavior.runToCompletion(previousGames, currentGame)
        println(JsonMappers.pretty.writeValueAsString(finalGame.toObject()))
    }
}