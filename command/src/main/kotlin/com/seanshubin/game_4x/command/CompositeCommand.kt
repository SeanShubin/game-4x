package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class CompositeCommand(val list: List<LandCommand>) : LandCommand {
    constructor(vararg landCommand: LandCommand) : this(landCommand.toList())

    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        var current: Either<Failure, Land> = land.right()
        list.forEach { command ->
            current = current.flatMap { command.execute(it) }
        }
        return current
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
