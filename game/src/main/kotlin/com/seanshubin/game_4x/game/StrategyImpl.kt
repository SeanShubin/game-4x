package com.seanshubin.game_4x.game

class StrategyImpl(val commands:List<Command>):Strategy {
    override fun takeTurn(game: Game): Game {
        var currentGame = game
        commands.forEach{
            currentGame = it.execute(currentGame)
        }
        return currentGame
    }
}
