package com.seanshubin.game_4x.prototype

object BuildFarm : AllLandsCommand() {
    override fun toObject(): String = "BuildFarm"

    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result =
        BuildGatherer(planetName, landIndex, Names.FOOD).execute(game)
}