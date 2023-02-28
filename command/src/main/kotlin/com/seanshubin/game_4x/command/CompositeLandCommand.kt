package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.Land

data class CompositeLandCommand(val list: List<LandCommand>) : LandCommand {
    constructor(vararg landCommand: LandCommand) : this(landCommand.toList())

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
            listOf("composite land: ${list.size}") + allDetails.indent()
        }
        return current.map { it.copy(details = details) }
    }

    private fun appendDetails(
        details: MutableList<String>,
        result: Either<LandFailure, LandSuccess>
    ): Either<LandFailure, LandSuccess> {
        when (result) {
            is Either.Right -> {
                if (result.value.details.isNotEmpty()) {
                    details.add(JsonMappers.compact.writeValueAsString(result.value.command))
                    details.addAll(result.value.details.indent())
                }
            }
            else -> {}
        }
        return result
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
