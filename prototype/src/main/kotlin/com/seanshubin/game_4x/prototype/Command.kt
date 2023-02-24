package com.seanshubin.game_4x.prototype

interface Command {
    fun toObject(): Any
    fun execute(game: Game): Result
}