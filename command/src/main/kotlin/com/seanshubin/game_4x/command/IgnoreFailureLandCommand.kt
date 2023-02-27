package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class IgnoreFailureLandCommand(val delegate: LandCommand) : LandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val result = delegate.execute(land)
        return if (result.isLeft()) land.right()
        else result
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "delegate" to delegate.toObject()
    )
}