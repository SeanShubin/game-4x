package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class CompositeUniverseCommand(val list: List<UniverseCommand>) : UniverseCommand {
    constructor(vararg universeCommand: UniverseCommand) : this(universeCommand.toList())

    override fun execute(universe: Universe): Either<Failure, Universe> {
        var current: Either<Failure, Universe> = universe.right()
        list.forEach { command ->
            current = current.flatMap { command.execute(it) }
        }
        return current
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
