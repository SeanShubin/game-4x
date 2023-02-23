package com.seanshubin.game_4x.game

data class CompositeCommand(val name:String, val list: List<Command>) : Command {
    override fun toObject(): List<Any> = list.map { it.toObject() }

    override fun execute(game: Game): Result {
        var currentGame = game
        val accumulatedDetails = mutableListOf<String>()
        var success = true
        list.forEach {
            val result = it.execute(currentGame)
            accumulatedDetails.addAll(result.details)
            if (result.success) {
                currentGame = result.game
            } else {
                success = false
            }
        }
        val details = listOf(name) + accumulatedDetails.map{"  $it"}
        return if (success) {
            Result(this, currentGame, success = true, details)
        } else {
            Result(this, game, success = false, details)
        }
    }
}
