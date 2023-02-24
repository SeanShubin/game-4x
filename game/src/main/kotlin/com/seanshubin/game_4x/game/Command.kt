package com.seanshubin.game_4x.game

interface Command {
    fun execute(universe:Universe):Result
}