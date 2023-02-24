package com.seanshubin.game_4x.prototype

object GenerateFoodWherePossible : AllLandsCommand() {
    override fun toObject(): String = "GenerateFoodWherePossible"

    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result =
        GenerateFoodCommand(planetName, landIndex).execute(game)
}