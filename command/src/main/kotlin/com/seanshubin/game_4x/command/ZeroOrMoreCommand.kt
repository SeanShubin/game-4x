package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class ZeroOrMoreCommand(val delegate:LandCommand):LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        DebugCommand.debug(this)
        var current = land
        var times = 0
        do {
            val result = delegate.execute(current)
            if(result is Either.Right){
                times++
                current = result.value.land
            }
        } while(result.isRight())
        return LandSuccess(this, current, "zero or more: $times").right()
    }

    override fun toObject():Map<String, Any> = mapOf("delegate" to delegate.toObject())
}
