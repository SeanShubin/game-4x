package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.game.*
import org.junit.Test

class CommandRunnerTest {
    @Test
    fun colonize(){
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)

        val updateLand = {land: Land ->
            land.addThing(node).addThing(colonizer)
        }
        val updatePlanet = {planet: Planet ->
            planet.addLand().updateLand(landIndex, updateLand)
        }
        val universe = Universe()
            .addPlanet(planetName)
            .updatePlanet(planetName, updatePlanet)
        val command = EveryLandCommand(ColonizeLandCommand)
        val commandRunner = CommandRunnerImpl(command)

        // when
        val history = commandRunner.runToCompletion(universe)

        // then
        history.forEach(::println)
    }

}