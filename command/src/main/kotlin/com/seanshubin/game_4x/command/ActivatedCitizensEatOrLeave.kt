package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Things

object ActivatedCitizensEatOrLeave : LandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<LandFailure, Land> {
        DebugCommand.debug(this)
        val citizen = Things.createCitizen(activated = true)
        return EatOrLeave.execute(land, citizen)
    }
}
