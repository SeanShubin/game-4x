package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Planet
import com.seanshubin.game_4x.game.Things
import com.seanshubin.game_4x.game.Universe
import org.junit.Test

class CommandRunnerTest {
    @Test
    fun colonize() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)

        val updateLand = { land: Land ->
            land.addThing(node).addThing(colonizer)
        }
        val updatePlanet = { planet: Planet ->
            planet.addLand().updateLand(landIndex, updateLand)
        }
        val universe = Universe()
            .addPlanet(planetName)
            .updatePlanet(planetName, updatePlanet)
        val command = EveryLandCommand(IgnoreFailureLandCommand(ColonizeLandCommand))
        val commandRunner = CommandRunnerImpl(command)

        // when
        val history = commandRunner.runToCompletion(universe)

        // then
        history.forEach { println(JsonMappers.pretty.writeValueAsString(it.toObject())) }
    }

}