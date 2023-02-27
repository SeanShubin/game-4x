package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class ZeroOrMoreCommand(val delegate:SingleLandCommand):SingleLandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        var current = land
        do {
            val result = delegate.execute(current)
            if(result is Either.Right){
                current = result.value
            }
        } while(result.isRight())
        return current.right()
    }

    override fun toObject():Map<String, Any> = mapOf("delegate" to delegate.toObject())
}
