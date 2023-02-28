package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Planet
import com.seanshubin.game_4x.game.Things
import com.seanshubin.game_4x.game.Universe
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class UniverseCommandRunnerTest {
    @Test
    fun runToCompletion() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val density = 6
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density)

        val updateLand = { land: Land ->
            land.addThing(node).addThing(colonizer)
        }
        val updatePlanet = { planet: Planet ->
            planet.addLand().updateLand(landIndex, updateLand)
        }
        val universe = Universe()
            .addPlanet(planetName)
            .updatePlanet(planetName, updatePlanet)
        val command = EveryLandUniverseCommand(GenericLandStrategy)
        val basePath = Paths.get("generated")
        val newUniverseEvent = {turn:Int, newUniverse:Universe ->
            writeTurn(basePath, turn, newUniverse)
        }
        val turnLimit = 10
        val commandRunner = CommandRunnerImpl(command, newUniverseEvent, turnLimit)

        // when
        clearHistory(basePath)
        val history = commandRunner.runToCompletion(universe)

        // then
    }

    private fun clearHistory(basePath: Path){
        Files.createDirectories(basePath)
        Files.list(basePath).toList().forEach { file ->
            Files.delete(file)
        }
    }

    private fun writeTurn(basePath: Path, turn: Int, universe: Universe) {
        val fileName = "turn-$turn.json"
        val path = basePath.resolve(fileName)
        val json = JsonMappers.pretty.writeValueAsString(universe.toObject())
        Files.writeString(path, json)
    }
}

/*
colonize
{name:colonizer} - 1
{name:node, resource:food} > 0
{name:citizen} = 0
{name:citizen} + 1
{name:gatherer, resource:food} + 1

run-food-gatherer
a = {name:node, resource:food, activated:false } - 1
{name:node, resource:food, activated:true } + 1
{name:gatherer, resource:food, activated:false} - 1
{name:gatherer, resource:food, activated:true} + 1
{name:citizen, activated:false} - 1
{name:citizen, activated:true} + 1
{name:supply, resource:food} + a.density

new-citizens-enter
a = {name:citizen}
b = {name:supply, resource:food}
c = min(a,b)
{name:citizen} + c

build-farm
{name:gatherer, resource:food} < {name:node, resource:food}
{name:citizen, activated:false} - 1
{name:citizen, activated:true} + 1
{name:gatherer, resource:food} + 1

feed-citizen
{supply:citizen, resource:food} - 1
{name:citizen, activated:true} - 1
{name:citizen, activated:false} - 1

activated-citizens-leave
{name:citizen, activated:true} - 1

reset-gatherer
{name:gatherer, resource:food, activated:true} - 1
{name:gatherer, resource:food, activated:false} + 1

reset-node
{name:node, resource:food, activated:true } - 1
{name:node, resource:food, activated:false } + 1
*/
