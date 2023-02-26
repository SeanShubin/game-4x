package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class IgnoreFailureCommand(val delegate: Command) : Command {
    override fun execute(universe: Universe): Either<Failure, Universe> {
        val result = delegate.execute(universe)
        return if (result.isLeft()) universe.right()
        else result
    }
}