package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing
import com.seanshubin.game_4x.game.Things

data class BuildGathererCommand(val resource: String) : LandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val nodeQuery = Thing(
            "name" to "node",
            "resource" to resource
        )
        val gathererQuery = Thing(
            "name" to "gatherer",
            "resource" to resource
        )
        val gatherer = Things.createGatherer(resource)
        val usedCitizen = Things.createCitizen(activated = true)
        val unUsedCitizen = Things.createCitizen()

        val commands = listOf(
            LessThanCommand(gathererQuery, nodeQuery),
            RemoveCommand(unUsedCitizen),
            AddCommand(usedCitizen),
            AddCommand(gatherer)
        )
        val result = CompositeLandCommand(this, commands).execute(land)
        return result
    }
}
