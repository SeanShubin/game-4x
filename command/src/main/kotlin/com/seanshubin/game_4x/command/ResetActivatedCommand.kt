package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

object ResetActivatedCommand : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val anyActivated = Thing("activated" to true)
        val list = land.findUnique(anyActivated)
        val resetCommands: List<LandCommand> = list.flatMap {
            val quantity = land.quantityByThing[it] ?: 0
            val thingThatWasReset = it.setBooleanValue("activated", false)
            listOf(
                RemoveCommand(it, quantity),
                AddCommand(thingThatWasReset, quantity)
            )

        }
        return if (resetCommands.isEmpty()) return LandSuccess(this, land, "nothing to reset").right()
        else CompositeLandCommand(this, resetCommands).execute(land)
    }

    override fun toObject(): String = this.javaClass.simpleName
}
