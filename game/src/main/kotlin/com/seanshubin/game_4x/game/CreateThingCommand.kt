package com.seanshubin.game_4x.game

data class CreateThingCommand(
    val planetName: String,
    val landIndex: Int,
    val thing: Thing,
    val quantity:Int = 1
) : Command {
    override fun execute(universe: Universe): Universe =
        universe.updateLand(planetName, landIndex) { land ->
            land.addThing(thing, quantity)
        }
}
