package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Planet
import com.seanshubin.game_4x.game.Universe

data class EveryLandCommand(val singleLandCommand: SingleLandCommand) : Command {
    override fun execute(universe: Universe): Either<String, Universe> {
        val initialValue:Either<String, Universe> = universe.right()
        val foldPlanetFunction = { currentPlanetValue:Either<String, Universe>, planet: Planet ->
            val foldLandFunction = { currentLandValue:Either<String, Universe>, land: Land ->
                currentLandValue.flatMap { universe:Universe ->
                    val landResult = singleLandCommand.execute(land)
                    landResult.flatMap { land:Land ->
                        val newUniverse:Universe = universe.updateLand(land.planetName, land.index, { land })
                        newUniverse.right()
                    }
                }
            }
            planet.lands.fold(currentPlanetValue, foldLandFunction)
        }
        val finalValue = universe.planets.fold(initialValue, foldPlanetFunction)
        return finalValue
    }
}
