package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

object ResetSupplyCommand:SingleLandCommand {
    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val anySupply = Thing("name" to "supply")
        val supplyList = land.fullMatchesFor(anySupply)
        val removeSupplyCommands = supplyList.map {
            val quantity = land.quantityByThing[it] ?: 0
            RemoveCommand(it, quantity)
        }
        return CompositeCommand(removeSupplyCommands).execute(land)
    }

    override fun toObject(): String = this.javaClass.simpleName
}
