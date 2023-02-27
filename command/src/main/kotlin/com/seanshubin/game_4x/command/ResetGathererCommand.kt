package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

object ResetGathererCommand:SingleLandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val anyActivatedGatherer = Thing("name" to "gatherer", "activated" to true)
        val gathererList = land.fullMatchesFor(anyActivatedGatherer)
        val resetGathererCommands:List<SingleLandCommand> = gathererList.flatMap {
            val quantity = land.quantityByThing[it] ?: 0
            val gathererThatWasReset = it.setBooleanValue("activated", false)
            listOf(
                RemoveCommand(it, quantity),
                AddCommand(gathererThatWasReset, quantity)
            )

        }
        return if(resetGathererCommands.isEmpty()) return land.right()
        else CompositeCommand(resetGathererCommands).execute(land)
    }

    override fun toObject(): String = this.javaClass.simpleName
}
