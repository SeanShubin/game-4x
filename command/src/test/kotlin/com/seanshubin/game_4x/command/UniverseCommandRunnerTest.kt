package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Planet
import com.seanshubin.game_4x.game.Things
import com.seanshubin.game_4x.game.Universe
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.Test

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
            land.addThing(node, 4).addThing(colonizer)
        }
        val updatePlanet = { planet: Planet ->
            planet.addLand().updateLand(landIndex, updateLand)
        }
        val universe = Universe()
            .addPlanet(planetName)
            .updatePlanet(planetName, updatePlanet)
        val command = EveryLandUniverseCommand(GenericLandStrategy)
        val basePath = Paths.get("generated")
        val newUniverseEvent = { turn: Int, newUniverse: Universe ->
            writeTurn(basePath, turn, newUniverse)
        }
        val turnLimit = 10
        val commandRunner = CommandRunnerImpl(command, newUniverseEvent, turnLimit)

        // when
        clearHistory(basePath)
        val history = commandRunner.runToCompletion(universe)

        // then
    }

    private fun clearHistory(basePath: Path) {
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
required has-food-node
required no-citizens
required remove-colonizer
optional add-citizen
optional add-farm

has-food-node
greater-than 0 {node, resource:food}

no-citizens
equal-to 0 {citizen}

remove-colonizer
remove {colonizer}

add-citizen
add {citizen, activated:false}

add-farm
less-than {gatherer, resource:food}                  {node, resource:food}
add       {gatherer, resource:food, activated:false}

test-case
input
  1 {colonizer}
  1 {node resource:food}
output
  1 -> 0 {colonizer}
  0 -> 1 {citizen activated:false}
  0 -> 1 {farm resource:food activated:false}
expected
  1 {node resource:food}
  1 {gatherer resource:food}
  1 {citizen activated:false}

test-case
name
  typical
input
  1 {colonizer}
  1 {node resource:food}
output
  1 -> 0 {colonizer}
  0 -> 1 {citizen activated:false}
  0 -> 1 {farm resource:food activated:false}
expected
  1 {node resource:food}
  1 {gatherer resource:food}
  1 {citizen activated:false}

test-case
name
  farm-already-exists
input
  1 {colonizer}
  2 {node resource:food}
  1 {gatherer resource:food}
output
  1 -> 0 {colonizer}
  0 -> 1 {citizen activated:false}
  0 -> 1 {farm resource:food activated:false}
expected
  2 {node resource:food}
  2 {gatherer resource:food}
  1 {citizen activated:false}

test-case
name
  farm-already-at-max
input
  1 {colonizer}
  1 {node resource:food}
  1 {gatherer resource:food}
output
  1 -> 0 {colonizer}
  0 -> 1 {citizen activated:false}
expected
  1 {node resource:food}
  1 {gatherer resource:food}
  1 {citizen activated:false}

----------

harvest-food
required activate-food-node
required activate-food-gatherer
required activate-citizen
optional add-food

activate-food-node
find-first {node resource:food activated:false} {sort-by:density sort-order:descending, alias:found-node}
activate found-node

activate-food-gatherer
activate {gatherer resource:food}

activate-citizen
activate {citizen}

add-food
add {supply resource:food} found-node.density

sample-output
harvest-food
  activated {node resource:food}
  activated {gatherer resource:food}
  0 -> 6 {supply resource:food}

----------

build-food-gatherer
required less-gatherers-than-nodes
required activate-citizen
optional add-gatherer

less-gatherers-than-nodes
less-than {gatherer resource:food} {node resource:food}
activate {citizen}
add {gatherer resource:food}

sample-output
build-food-gatherer
  activated {citizen}
  0 -> 1 {gatherer resource:food}

*/

