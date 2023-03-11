package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land

object GenericLandStrategy : LandCommand {
    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val commands = listOf(
            ZeroOrMoreCommand(ColonizeLandCommand),
            ZeroOrMoreCommand(RunGathererCommand("food")),
            ZeroOrMoreCommand(BuildGathererCommand("food")),
            ActivatedCitizensEatOrLeave,
            NonActivatedCitizensEatOrLeave,
            NewCitizensEnterCommand,
            DiscardSupplyCommand,
            ResetActivatedCommand,
        )
        val result = CompositeLandCommand(this, commands).execute(land)
        return result
    }

    override fun toObject(): String = this.javaClass.simpleName
}
