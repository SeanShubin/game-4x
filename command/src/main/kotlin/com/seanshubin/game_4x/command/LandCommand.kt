package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Land

interface LandCommand : HasToObject {
    fun execute(land: Land): Either<Failure, Land>
}