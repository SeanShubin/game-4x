package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.Result.Companion.failure
import com.seanshubin.game_4x.game.Result.Companion.success

data class CreateLandCommand(val planetName:String):Command {
    override fun execute(universe: Universe): Result =
        if(universe.containsPlanet(planetName)){
            success("Added land to planet '$planetName'", universe.addLand(planetName))
        } else {
            failure("Planet '$planetName' does not exist")
        }
}