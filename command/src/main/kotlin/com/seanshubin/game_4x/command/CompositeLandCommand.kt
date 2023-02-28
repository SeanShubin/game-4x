package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land

data class CompositeLandCommand(val parent:LandCommand, val list: List<LandCommand>) : LandCommand {
//    constructor(parent:LandCommand, vararg landCommand: LandCommand) : this(parent, landCommand.toList())

    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        var current: Either<LandFailure, LandSuccess> =
            LandSuccess(this, land, emptyList()).right()
        val allResults = mutableListOf<Either<LandFailure, LandSuccess>>()
        list.forEach { command ->
            current = current.flatMap {
                val result= command.execute(it.land)
                allResults.add(result)
                result
            }
        }
        val allDetails = allResults.mapNotNull{it.getOrNull()}.flatMap{it.details}
        val details = if (allDetails.isEmpty()) {
            emptyList()
        } else {
            listOf(JsonMappers.compact.writeValueAsString(parent.toObject())) + allDetails.indent()
        }
        return current.map { it.copy(details = details) }
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
