package com.seanshubin.game_4x.game

interface Command {
    fun execute(game:Game):Game
    companion object {
        fun foldFunction(game:Game, command:Command):Game = command.execute(game)
    }
}
