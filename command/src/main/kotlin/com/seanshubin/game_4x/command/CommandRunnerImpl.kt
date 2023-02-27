package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Universe

class CommandRunnerImpl(
    private val universeCommand: UniverseCommand,
    private val newUniverse: (Int, Universe) -> Unit,
    private val turnLimit: Int
) : CommandRunner {
    override fun runToCompletion(universe: Universe): List<Universe> {
        var current = universe
        var turn = 0
        val history = mutableListOf<Universe>()
        do {
            newUniverse(turn, current)
            turn++
            history.add(current)
            val result = universeCommand.execute(current)
            current = when (result) {
                is Either.Right -> result.value
                is Either.Left -> throw RuntimeException(result.value.toString())
            }
            if(turn > turnLimit){
                throw RuntimeException("Turn limit $turnLimit exceeded")
            }
        } while (!history.contains(current))
        return history
    }
}