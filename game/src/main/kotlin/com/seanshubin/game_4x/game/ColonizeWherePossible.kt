package com.seanshubin.game_4x.game

object ColonizeWherePossible : AllLandsCommand() {
    override fun toObject(): String = "ColonizeWherePossible"

    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result =
        ColonizeCommand(planetName, landIndex).execute(game)
}
