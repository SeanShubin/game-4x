package com.seanshubin.game_4x.game
import com.seanshubin.game_4x.game.Result.Companion.failure
import com.seanshubin.game_4x.game.Result.Companion.success

data class CreatePlanetCommand(val name:String):Command {
    override fun execute(universe: Universe): Result =
        if(universe.containsPlanet(name)){
            failure("Planet named '$name' already exists")
        } else {
            val newUniverse = universe.addPlanet(name)
            success("Created planet '$name'", newUniverse)
        }
}