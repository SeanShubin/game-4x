package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

object CommandConverters {
    fun LandCommand.toUniverseCommand(planetName: String, landIndex: Int): UniverseCommand =
        object : UniverseCommand {
            override fun execute(universe: Universe): Either<Failure, Universe> {
                val land = universe.getLand(planetName, landIndex)
                return when (val result = this@toUniverseCommand.execute(land)) {
                    is Either.Right -> universe.setLand(planetName, landIndex, result.value).right()
                    is Either.Left -> result.value.left()
                }
            }

            override fun toObject(): Any = this@toUniverseCommand.toObject()
        }


}