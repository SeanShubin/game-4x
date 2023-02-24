package com.seanshubin.game_4x.game


data class CreatePlanetCommand(val name: String) : Command {
    override fun execute(universe: Universe): Universe = universe.addPlanet(name)
}
