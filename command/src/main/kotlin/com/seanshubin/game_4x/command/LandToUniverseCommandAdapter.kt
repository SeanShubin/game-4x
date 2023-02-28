package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class LandToUniverseCommandAdapter(
    val planetName:String,
    val landIndex:Int,
    val landCommand:LandCommand
):UniverseCommand {
    override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
        val land = universe.getLand(planetName, landIndex)
        val landResult = landCommand.execute(land)
        val result = when (landResult) {
            is Either.Right -> {
                val newUniverse = universe.setLand(planetName, landIndex, landResult.value.land)
                val message= "planet '$planetName', land $landIndex: ${landResult.value.message}"
                val children = landResult.value.children.map { it.toUniverseSuccess(this, newUniverse) }
                UniverseSuccess(this, newUniverse, message, children).right()
            }
            is Either.Left -> UniverseFailure(this, universe, landResult.value.message).left()
        }
        return result
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "landCommand" to landCommand.toObject()
    )

    private fun LandSuccess.toUniverseSuccess(command:UniverseCommand, universe:Universe):UniverseSuccess {
        val newChildren = children.map{it.toUniverseSuccess(command, universe)}
        return UniverseSuccess(command, universe, message, newChildren)
    }
}