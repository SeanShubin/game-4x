package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

data class AddCommand(val target: Thing, val quantity: Int = 1) : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val oldQuantity = land.quantityByThing[target] ?: 0
        val newQuantity = oldQuantity + quantity
        val newLand: Land = land.setQuantity(target, newQuantity)
        val message = "changed quantity from $oldQuantity to $newQuantity for $target"
        return LandSuccess(this, newLand, message).right()
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "target" to target.toObject(),
        "quantity" to quantity
    )
}
