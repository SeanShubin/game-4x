package com.seanshubin.game_4x.prototype

abstract class AllLandsCommand : AllPlanetsCommand() {
    override fun executeOnPlanet(game: Game, planetName: String): Result {
        var currentGame = game
        val details = mutableListOf<String>()
        (0 until game.landCount(planetName)).forEach {
            val result = executeOnLand(currentGame, planetName, it)
            if (result.success) {
                currentGame = result.game
                details.addAll(result.details)
            }
        }
        return Result(this, currentGame, success = true, details)
    }

    abstract fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result
}