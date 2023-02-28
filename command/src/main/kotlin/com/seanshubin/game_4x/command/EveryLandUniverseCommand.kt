package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.filterOrElse
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.command.CommandConverters.toUniverseCommand
import com.seanshubin.game_4x.game.Universe

data class EveryLandUniverseCommand(val landCommand: LandCommand) : UniverseCommand {
    override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
        val universeCommandList: List<UniverseCommand> = universe.planets.flatMap { planet ->
            List(planet.lands.size) { landIndex ->
                landCommand.toUniverseCommand(planet.name, landIndex)
            }
        }
        val allResults = mutableListOf<Either<UniverseFailure, UniverseSuccess>>()
        val initialValue: Either<UniverseFailure, UniverseSuccess> =
            UniverseSuccess(this, universe, listOf()).right()
        val executeCommand = { accumulator: Either<UniverseFailure, UniverseSuccess>,
                               command: UniverseCommand ->
            val result = accumulator.flatMap { command.execute(it.universe) }
            allResults.add(result)
            result
        }
        val lastValue = universeCommandList.fold(initialValue, executeCommand)
        val finalValue = lastValue.map { result ->
            val details = allResults.mapNotNull{ it.getOrNull()}.flatMap{it.details}
            result.copy(details = details)
        }
        return finalValue
    }

    override fun toObject(): Any = landCommand.toObject()
}
