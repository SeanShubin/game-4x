package com.seanshubin.game_4x.game

interface Command {
    fun toObject(): Any
    fun execute(game: Game): Result
}
