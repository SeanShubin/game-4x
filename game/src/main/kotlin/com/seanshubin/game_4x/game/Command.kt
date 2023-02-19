package com.seanshubin.game_4x.game

interface Command {
    fun execute(game:Game):Game
}
