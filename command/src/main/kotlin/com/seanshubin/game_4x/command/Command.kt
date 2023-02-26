package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Universe

interface Command {
    fun execute(universe: Universe): Either<Failure, Universe>
}