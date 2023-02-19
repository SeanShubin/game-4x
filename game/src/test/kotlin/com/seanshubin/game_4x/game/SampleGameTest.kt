package com.seanshubin.game_4x.game

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class SampleGameTest {
    @Test
    fun colonize(){
        val planetResourcesA = Resources(listOf(
            Resource(Names.FOOD, current = 0, maximum=4, rate = 4),
            Resource(Names.MATERIAL, current = 0, maximum = 2, rate = 7),
            Resource(Names.ENERGY, current = 0, maximum = 6, rate = 5)
        ))
        val planetA = Planet.empty.setLandsWithResources(10, planetResourcesA).addOrbital("colonizer")
        val planetResourcesB = Resources(listOf(
            Resource(Names.FOOD, current = 0, maximum = 2, rate = 3),
            Resource(Names.MATERIAL, current = 0, maximum = 8, rate = 6),
            Resource(Names.ENERGY, current = 0, maximum = 3, rate = 4)
        ))
        val planetB = Planet.empty.setLandsWithResources(6, planetResourcesB)
        val commands = listOf(Colonize)
        val strategy:Strategy = StrategyImpl(commands)
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