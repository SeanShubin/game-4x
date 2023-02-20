package com.seanshubin.game_4x.game

class StrategyImpl(private val commands:List<Command>):Strategy {
    override fun takeTurn(game: Game): Game {
        var currentGame = game.endTurn()
        commands.forEach{
            currentGame = it.execute(currentGame)
        }
        return currentGame
    }
}
