package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.game.Land

data class ZeroOrMoreCommand(val delegate:LandCommand):LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        var current = land
        var times = 0
        val children = mutableListOf<LandSuccess>()
        do {
            val result = delegate.execute(current)
            when( result){
                is Either.Right -> {
                    times++
                    current = result.value.land
                    children.add(result.value)
                }
                else -> {}
            }
        } while(result.isRight())
        val message = "times: $times"
        return LandSuccess(this, current, message, children).right()
    }

    override fun toObject():Map<String, Any> = mapOf("delegate" to delegate.toObject())
}
