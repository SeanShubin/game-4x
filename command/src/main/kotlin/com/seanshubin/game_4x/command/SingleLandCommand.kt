package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land

interface SingleLandCommand {
    fun execute(land: Land): Either<String, Land>
}