package com.seanshubin.game_4x.prototype

interface Strategy {
    fun takeTurn(game: Game): Game
}