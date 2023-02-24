package com.seanshubin.game_4x.prototype

abstract class AllPlanetsCommand : Command {
    override fun execute(game: Game): Result {
        var currentGame = game
        val details = mutableListOf<String>()
        game.planetNames().forEach {
            val result = executeOnPlanet(currentGame, it)
            if (result.success) {
                currentGame = result.game
                details.addAll(result.details)
            }
        }
        return Result(this, currentGame, success = true, details)
    }

    abstract fun executeOnPlanet(game: Game, planetName: String): Result
}