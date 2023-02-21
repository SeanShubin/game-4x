package com.seanshubin.game_4x.game

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class SampleGameTest {
    @Test
    fun colonize() {
        val planetResourcesA = Resources(
            listOf(
                Resource(Names.FOOD, density = 4, inGround = 4),
                Resource(Names.MATERIAL, density = 7, inGround = 2),
                Resource(Names.ENERGY, density = 5, inGround = 6)
            )
        )
        val planetA = Planet("Planet A").setLandsWithResources(2, planetResourcesA).addOrbital("colonizer")
        val planetResourcesB = Resources(
            listOf(
                Resource(Names.FOOD, density = 3, inGround = 2),
                Resource(Names.MATERIAL, density = 6, inGround = 8),
                Resource(Names.ENERGY, density = 4, inGround = 3)
            )
        )
        val planetB = Planet("Planet B").setLandsWithResources(2, planetResourcesB)
        val commands = listOf(
            Colonize,
            GenerateFood,
            GenerateLabor,
            BuildFarm
        )
        val strategy: Strategy = StrategyImpl(commands)
        val gameBehavior = GameBehaviorImpl(strategy)
        val initialGames = listOf(Game(planets = listOf(planetA, planetB)))
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
 */