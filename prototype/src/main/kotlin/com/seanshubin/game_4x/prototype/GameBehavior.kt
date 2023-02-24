package com.seanshubin.game_4x.prototype

interface GameBehavior {
    fun runToCompletion(games: List<Game>): List<Game>
}