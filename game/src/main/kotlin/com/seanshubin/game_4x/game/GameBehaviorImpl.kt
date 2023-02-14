package com.seanshubin.game_4x.game

class GameBehaviorImpl(private val strategy:Strategy): GameBehavior {
    override tailrec fun runToCompletion(previousGames:List<GameState>, currentGame:GameState):GameState {
        if(previousGames.contains(currentGame)) return currentGame
        val nextGame = strategy.takeTurn(currentGame)
        return runToCompletion(previousGames + currentGame, nextGame)
    }
}
