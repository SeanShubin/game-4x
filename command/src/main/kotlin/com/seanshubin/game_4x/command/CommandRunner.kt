package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.game.Universe

interface CommandRunner {
    fun runToCompletion(universe: Universe): List<Universe>
}
