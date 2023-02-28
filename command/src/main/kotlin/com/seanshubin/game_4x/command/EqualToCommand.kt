package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing

data class EqualToCommand(val thing: Thing, val target: Int) : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        DebugCommand.debug(this)
        val quantity: Int = land.countPartiallyMatches(thing)
        return if (quantity == target) {
            LandSuccess(this, land, "expected $target = actual $quantity").right()
        } else {
            LandFailure(
                this,
                land,
                "$quantity is not equal to $target"
            ).left()
        }
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "thing" to thing.toObject(),
        "target" to target
    )
}
