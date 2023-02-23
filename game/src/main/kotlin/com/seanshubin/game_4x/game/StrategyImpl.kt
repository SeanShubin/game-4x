package com.seanshubin.game_4x.game

class StrategyImpl(private val beforeTurn: Command, private val commands: List<Command>) : Strategy {
    override fun takeTurn(game: Game): Game {
        val beforeTurnResult = beforeTurn.execute(game)
        beforeTurnResult.toLines().forEach(::println)
        beforeTurnResult.assertSuccess()
        var currentGame = beforeTurnResult.game
        commands.forEach {
            val result = it.execute(currentGame)
            result.toLines().forEach(::println)
            result.assertSuccess()
            currentGame = result.game
        }
        return currentGame
    }
}
