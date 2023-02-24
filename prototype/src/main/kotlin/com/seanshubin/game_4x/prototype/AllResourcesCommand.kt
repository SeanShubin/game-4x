package com.seanshubin.game_4x.prototype

abstract class AllResourcesCommand : AllLandsCommand() {
    override fun executeOnLand(game: Game, planetName: String, landIndex: Int): Result {
        var currentGame = game
        val details = mutableListOf<String>()
        game.resourceNames(planetName, landIndex).forEach {
            val result = executeOnResource(game, planetName, landIndex, it)
            if (result.success) {
                currentGame = result.game
                details.addAll(result.details)
            }
        }
        return Result(this, currentGame, success = true, details)
    }

    abstract fun executeOnResource(game: Game, planetName: String, landIndex: Int, resourceName: String): Result
}