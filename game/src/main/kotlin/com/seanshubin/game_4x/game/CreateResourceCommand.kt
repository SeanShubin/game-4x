package com.seanshubin.game_4x.game

data class CreateResourceCommand(
    val planetName: String,
    val landIndex: Int,
    val name: String,
    val prevalence: Int,
    val density: Int
) : Command {
    override fun execute(universe: Universe): Universe =
        universe.updateLand(planetName, landIndex) { land ->
            land.addThings(List(prevalence){ Resource(name, density)})
        }
}
