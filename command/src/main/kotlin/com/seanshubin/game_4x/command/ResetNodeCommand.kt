package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

object ResetNodeCommand:SingleLandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val anyActivatedNode = Thing("name" to "node", "activated" to true)
        val nodeList = land.fullMatchesFor(anyActivatedNode)
        val resetNodeCommands:List<SingleLandCommand> = nodeList.flatMap {
            val quantity = land.quantityByThing[it] ?: 0
            val nodeThatWasReset = it.setBooleanValue("activated", false)
            listOf(
                RemoveCommand(it, quantity),
                AddCommand(nodeThatWasReset, quantity)
            )

        }
        return if(resetNodeCommands.isEmpty()) return land.right()
        else CompositeCommand(resetNodeCommands).execute(land)
    }

    override fun toObject(): String = this.javaClass.simpleName
}
