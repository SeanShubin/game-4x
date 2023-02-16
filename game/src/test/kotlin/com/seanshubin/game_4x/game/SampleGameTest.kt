package com.seanshubin.game_4x.game

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class SampleGameTest {
    @Test
    fun colonize(){
        val planetResourcesA = Resources(listOf(
            Resource("food", quantity = 4, rate = 4),
            Resource("material", quantity = 2, rate = 7),
            Resource("energy", quantity = 6, rate = 5)
        ))
        val planetA = Planet("Planet A", size = 10, planetResourcesA).addOrbital("colonizer")
        val planetResourcesB = Resources(listOf(
            Resource("food", quantity = 2, rate = 3),
            Resource("material", quantity = 8, rate = 6),
            Resource("energy", quantity = 3, rate = 4)
        ))
        val planetB = Planet("Planet B", size = 6, planetResourcesB)
        val strategy:Strategy = StrategyImpl()
        val gameBehavior = GameBehaviorImpl(strategy)
        val previousGames = emptyList<GameState>()
        val currentGame = GameState.empty.addPlanet(planetA).addPlanet(planetB)
        val finalGame = gameBehavior.runToCompletion(previousGames, currentGame)
        val path = Paths.get("generated", "test.json")
        val json = JsonMappers.pretty.writeValueAsString(finalGame.toObject())
        Files.createDirectories(path.parent)
        Files.writeString(path, json)
    }
}