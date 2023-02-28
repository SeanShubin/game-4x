package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.game.Universe

object CommandConverters {
    fun LandCommand.toUniverseCommand(planetName: String, landIndex: Int): UniverseCommand =
        object : UniverseCommand {
            override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
                val land = universe.getLand(planetName, landIndex)
                return when (val result = this@toUniverseCommand.execute(land)) {
                    is Either.Right -> {
                        val newUniverse = universe.setLand(planetName, landIndex, result.value.land)
                        val details = if(result.value.details.isEmpty()){
                            emptyList()
                        } else {
                            listOf("planet '$planetName', land $landIndex") + result.value.details.indent()
                        }
                        UniverseSuccess(this, newUniverse, details).right()
                    }
                    is Either.Left -> UniverseFailure(this, universe, result.value.message).left()
                }
            }

            override fun toObject(): Any = this@toUniverseCommand.toObject()
        }


}