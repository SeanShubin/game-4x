package com.seanshubin.game_4x.game

class GameBehaviorImpl(private val strategy: Strategy) : GameBehavior {
    override tailrec fun runToCompletion(games: List<Game>): List<Game> {
        val currentGame = games.last()
        val nextGame = strategy.takeTurn(currentGame)
        return if (currentGame == nextGame)
            games
        else
            runToCompletion(games + nextGame)
    }
}
