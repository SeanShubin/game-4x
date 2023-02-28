package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class EveryLandUniverseCommand(val landCommand: LandCommand) : UniverseCommand {
    override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
        val universeCommandList: List<UniverseCommand> = universe.planets.flatMap { planet ->
            List(planet.lands.size) { landIndex ->
                LandToUniverseCommandAdapter(planet.name, landIndex, landCommand)
            }
        }
        return CompositeUniverseCommand(universeCommandList).execute(universe)
    }

    override fun toObject(): Any = landCommand.toObject()
}
