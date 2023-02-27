package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing
import com.seanshubin.game_4x.game.Things
import kotlin.math.min

object EatOrLeave {
    fun execute(land: Land, wantsToEat: Thing): Either<Failure, Land> {
        val foodSupply = Things.createSupply("food")
        val wantsToEatCount = land.quantityByThing[wantsToEat] ?: 0
        val foodSupplyCount = land.quantityByThing[foodSupply] ?: 0
        val wantsToEatLost = wantsToEatCount - foodSupplyCount
        val foodLost = min(foodSupplyCount, wantsToEatCount)
        val removeWantsToEatCommands =
            if (wantsToEatLost > 0) listOf(RemoveCommand(wantsToEat, wantsToEatLost))
            else emptyList()
        val removeFoodCommands =
            if (foodLost > 0) listOf(RemoveCommand(foodSupply, foodLost))
            else emptyList()
        val commands = removeWantsToEatCommands + removeFoodCommands
        val result =
            if (commands.isEmpty()) {
                land.right()
            } else {
                CompositeCommand(commands).execute(land)
            }
        return result
    }
}
