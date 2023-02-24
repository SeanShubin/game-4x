package com.seanshubin.game_4x.game

data class CreateLandCommand(val planetName: String) : Command {
    override fun execute(universe: Universe): Universe = universe.addLand(planetName)
}
