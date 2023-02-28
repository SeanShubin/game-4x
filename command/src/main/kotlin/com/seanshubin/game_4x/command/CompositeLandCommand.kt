package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land

data class CompositeLandCommand(val parent:LandCommand, val list: List<LandCommand>) : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        var currentLand = land
        val successResults = mutableListOf<LandSuccess>()
        list.forEach { command ->
            when(val result = command.execute(currentLand)){
                is Either.Right -> {
                    currentLand = result.value.land
                    successResults.add(result.value)
                }
                is Either.Left -> return result
            }
        }
        val result= LandSuccess(this, currentLand, "composite land ${list.size}", successResults).right()
        return result
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
