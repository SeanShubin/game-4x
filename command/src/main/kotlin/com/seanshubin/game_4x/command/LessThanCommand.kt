package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

data class LessThanCommand(val left: Thing, val right: Thing) : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val leftQuantity = land.countPartiallyMatches(left)
        val rightQuantity = land.countPartiallyMatches(right)
        return if (leftQuantity < rightQuantity) {
            LandSuccess(this, land, "$leftQuantity < $rightQuantity").right()
        } else {
            LandFailure(
                this,
                land,
                "$leftQuantity is not less than $rightQuantity"
            ).left()
        }
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "left" to left.toObject(),
        "right" to right.toObject()
    )
}
