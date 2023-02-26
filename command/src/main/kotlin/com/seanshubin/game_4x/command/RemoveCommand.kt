package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

data class RemoveCommand(val target: Thing, val quantity:Int = 1):SingleLandCommand {
    override fun execute(things: Land): Either<String, Land> {
        val oldQuantity = things.quantityByThing[target] ?: 0
        val newQuantity = oldQuantity - quantity
        return if(newQuantity < 0) {
            "can not reduce $oldQuantity by $quantity".left()
        } else {
            things.setQuantity(target, newQuantity).right()
        }
    }
}
