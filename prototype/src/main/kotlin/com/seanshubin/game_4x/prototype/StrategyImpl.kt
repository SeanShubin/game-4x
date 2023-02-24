package com.seanshubin.game_4x.prototype

class StrategyImpl(private val beforeTurn: Command, private val commands: List<Command>) : Strategy {
    override fun takeTurn(game: Game): Game {
        val beforeTurnResult = beforeTurn.execute(game)
        beforeTurnResult.details.forEach(::println)
        beforeTurnResult.assertSuccess()
        var currentGame = beforeTurnResult.game
        commands.forEach {
            val result = it.execute(currentGame)
            result.details.forEach(::println)
            result.assertSuccess()
            currentGame = result.game
        }
        return currentGame
    }
}