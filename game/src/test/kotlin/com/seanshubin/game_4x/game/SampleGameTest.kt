package com.seanshubin.game_4x.game

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class SampleGameTest {
    @Test
    fun colonize() {
        val planetResourcesA = Resources(
            listOf(
                Resource(Names.FOOD, density=4, inGround=4),
                Resource(Names.MATERIAL, density=7, inGround=2),
                Resource(Names.ENERGY, density=5, inGround=6)
            )
        )
        val planetA = Planet("Planet A").setLandsWithResources(10, planetResourcesA).addOrbital("colonizer")
        val planetResourcesB = Resources(
            listOf(
                Resource(Names.FOOD, density=3, inGround=2),
                Resource(Names.MATERIAL, density=6, inGround=8),
                Resource(Names.ENERGY, density=4, inGround=3)
            )
        )
        val planetB = Planet("Planet B").setLandsWithResources(6, planetResourcesB)
        val commands = listOf(Colonize, GenerateFood)
        val strategy: Strategy = StrategyImpl(commands)
        val gameBehavior = GameBehaviorImpl(strategy)
        val previousGames = emptyList<Game>()
        val currentGame = Game.empty.setPlanets(listOf(planetA, planetB))
        val finalGame = gameBehavior.runToCompletion(previousGames, currentGame)
        val path = Paths.get("generated", "test.json")
        val json = JsonMappers.pretty.writeValueAsString(finalGame.toObject())
        Files.createDirectories(path.parent)
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