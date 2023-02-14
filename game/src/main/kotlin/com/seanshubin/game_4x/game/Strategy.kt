package com.seanshubin.game_4x.game

interface Strategy {
    fun takeTurn(game:GameState):GameState
}
