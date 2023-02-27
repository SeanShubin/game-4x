package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Things
import kotlin.math.min

object NonActivatedCitizensEatOrLeave : SingleLandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val citizen = Things.createCitizen()
        return EatOrLeave.execute(land, citizen)
    }
}
