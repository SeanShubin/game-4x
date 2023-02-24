package com.seanshubin.game_4x.game

data class CreateResourceCommand(
    val planetName: String,
    val landIndex: Int,
    val name: String,
    val prevalence: Int,
    val density: Int
) : Command {
    override fun execute(universe: Universe): Universe =
        universe.addResource(planetName, landIndex, name, prevalence, density)
}
