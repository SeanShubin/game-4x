package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Universe

data class IgnoreFailureLandCommand(val delegate:SingleLandCommand):SingleLandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        val result = delegate.execute(land)
        return if(result.isLeft()) land.right()
        else result
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "delegate" to delegate.toObject()
    )
}