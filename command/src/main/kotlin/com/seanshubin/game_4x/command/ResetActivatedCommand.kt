package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

object ResetActivatedCommand:LandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val anyActivated = Thing("activated" to true)
        val list = land.fullMatchesFor(anyActivated)
        val resetCommands:List<LandCommand> = list.flatMap {
            val quantity = land.quantityByThing[it] ?: 0
            val thingThatWasReset = it.setBooleanValue("activated", false)
            listOf(
                RemoveCommand(it, quantity),
                AddCommand(thingThatWasReset, quantity)
            )

        }
        return if(resetCommands.isEmpty()) return land.right()
        else CompositeLandCommand(resetCommands).execute(land)
    }

    override fun toObject(): String = this.javaClass.simpleName
}
