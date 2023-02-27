package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.command.CommandConverters.toUniverseCommand
import com.seanshubin.game_4x.game.Universe

data class EveryLandUniverseCommand(val landCommand: LandCommand) : UniverseCommand {
    override fun execute(universe: Universe): Either<Failure, Universe> {
        val universeCommandList: List<UniverseCommand> = universe.planets.flatMap { planet ->
            List(planet.lands.size) { landIndex ->
                landCommand.toUniverseCommand(planet.name, landIndex)
            }
        }
        val initialValue: Either<Failure, Universe> = universe.right()
        val executeCommand = { accumulator: Either<Failure, Universe>,
                               command: UniverseCommand ->
            accumulator.flatMap { command.execute(it) }
        }
        val finalValue = universeCommandList.fold(initialValue, executeCommand)
        return finalValue
    }

    override fun toObject(): Any = landCommand.toObject()
}
