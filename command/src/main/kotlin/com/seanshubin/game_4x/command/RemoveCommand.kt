package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

data class RemoveCommand(val target: Thing, val quantity: Int = 1) : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val oldQuantity = land.quantityByThing[target] ?: 0
        val newQuantity = oldQuantity - quantity
        return if (newQuantity < 0) {
            LandFailure(
                this,
                land,
                "can not reduce $oldQuantity by $quantity"
            ).left()
        } else {
            val newLand = land.setQuantity(target, newQuantity)
            LandSuccess(this, newLand, "removed $quantity, from $oldQuantity to $newQuantity").right()
        }
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "target" to target.toObject(),
        "quantity" to quantity
    )
}
