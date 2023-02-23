package com.seanshubin.game_4x.game

object BuildFarmWherePossible : AllLandsCommand() {
    override fun toObject(): String = "BuildFarmWherePossible"

    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result =
        BuildFarmCommand(planetName, landIndex).execute(game)
}
