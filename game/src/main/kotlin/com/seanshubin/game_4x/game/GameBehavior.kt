package com.seanshubin.game_4x.game

interface GameBehavior {
    fun runToCompletion(previousGames:List<Game>, currentGame:Game):Game
}
