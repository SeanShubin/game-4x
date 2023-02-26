package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Land

interface SingleLandCommand: HasToObject{
    fun execute(land: Land): Either<Failure, Land>
}