package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Universe

class CommandRunnerImpl(private val command: Command) : CommandRunner {
    override fun runToCompletion(universe: Universe): List<Universe> {
        var current = universe
        val history = mutableListOf<Universe>()
        do {
            history.add(current)
            val result = command.execute(current)
            current = when (result) {
                is Either.Right -> result.value
                is Either.Left -> throw RuntimeException(result.value.toString())
            }
        } while (!history.contains(current))
        return history
    }
}