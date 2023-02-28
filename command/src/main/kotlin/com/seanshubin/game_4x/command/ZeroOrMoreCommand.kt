package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.game.Land

data class ZeroOrMoreCommand(val delegate:LandCommand):LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        var current = land
        var times = 0
        val detailsFromLoop = mutableListOf<String>()
        do {
            val result = delegate.execute(current)
            if(result is Either.Right){
                times++
                current = result.value.land
                detailsFromLoop.addAll(result.value.details)
            }
        } while(result.isRight())
        val details = if(times == 0){
            emptyList()
        } else {
            listOf("zero or more: $times") + detailsFromLoop.indent()
        }
        return LandSuccess(this, current, details).right()
    }

    override fun toObject():Map<String, Any> = mapOf("delegate" to delegate.toObject())
}
