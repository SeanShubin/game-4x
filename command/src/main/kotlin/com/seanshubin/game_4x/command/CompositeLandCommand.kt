package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class CompositeLandCommand(val list: List<LandCommand>) : LandCommand {
    constructor(vararg landCommand: LandCommand) : this(landCommand.toList())

    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        DebugCommand.debug(this)
        var current: Either<LandFailure, LandSuccess> = LandSuccess(this, land, "composite land command ${list.size}").right()
        list.forEach { command ->
            current = current.flatMap { command.execute(it.land) }
        }
        return current
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
