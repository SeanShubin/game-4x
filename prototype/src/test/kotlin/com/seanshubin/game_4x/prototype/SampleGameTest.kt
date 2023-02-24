package com.seanshubin.game_4x.prototype

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class SampleGameTest {
    @Test
    fun colonize() {
        val initialGame = Game()
            .addPlanet("Planet A")
            .addLand("Planet A", 2)
            .addResource("Planet A", landIndex = 0, resourceName = "food", prevalence = 4, density = 6)
            .addResource("Planet A", landIndex = 1, resourceName = "food", prevalence = 4, density = 6)
            .addOrbital("Planet A", landIndex = 0, "colonizer")
            .addPlanet("Planet B")
            .addLand("Planet B", 2)
            .addResource("Planet B", landIndex = 0, resourceName = "food", prevalence = 8, density = 3)
            .addResource("Planet B", landIndex = 1, resourceName = "food", prevalence = 8, density = 3)
        val commands = listOf(
            ColonizeWherePossible,
            GenerateFoodWherePossible,
            BuildFarmWherePossible
        )
        val beforeTurnCommand = RefreshCommand
        val strategy: Strategy = StrategyImpl(beforeTurnCommand, commands)
        val gameBehavior = GameBehaviorImpl(strategy)
        val initialGames = listOf(initialGame)
        val finalGames = gameBehavior.runToCompletion(initialGames)
        val basePath = Paths.get("generated")
        Files.createDirectories(basePath)
        removeFiles(basePath)
        finalGames.mapIndexed { index, game ->
            writeTurn(basePath, index, game)
        }
    }

    private fun removeFiles(basePath: Path) {
        Files.list(basePath).toList().forEach { file ->
            Files.delete(file)
        }
    }

    private fun writeTurn(basePath: Path, turn: Int, game: Game) {
        val fileName = "turn-$turn.json"
        val path = basePath.resolve(fileName)
        val json = JsonMappers.pretty.writeValueAsString(game.toObject())
        Files.writeString(path, json)
    }
}