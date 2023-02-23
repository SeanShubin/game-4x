package com.seanshubin.game_4x.game

object GenerateFood : AllLandsCommand() {
    override fun toObject(): String = "GenerateFood"

    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result =
        GenerateFoodCommand(planetName, landIndex).execute(game)
}
