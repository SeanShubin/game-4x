package com.seanshubin.game_4x.game

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
        removeFiles(basePath)
        Files.createDirectories(basePath)
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
/*
ColonizePlanet
BuildFarm
BuildMaterialMine
BuildEnergyMine
ExpandTerritory
BuildLauncher
BuildOrbitalFactory
BuildColonizer
BuildGate
SendColonizer

addPlanet(name="planet a")
selectPlanet(name="planet a")
addLand()
selectLand(index=0)
addResource(name="food", prevalence=6, density=4)
addObject(place="orbit", what="colonizer")
startGame()
colonize()
extractResource(resource="food")
convertFoodToLabor(times=4)
buildGatherer(resource="food")
 */