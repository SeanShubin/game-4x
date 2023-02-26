package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Land

data class CompositeCommand(val list:List<SingleLandCommand>):SingleLandCommand {
    constructor(vararg singleLandCommand:SingleLandCommand):this(singleLandCommand.toList())
    override fun execute(land: Land): Either<String, Land> {
        var current:Either<String, Land> = land.right()
        list.forEach{ command ->
            current = current.flatMap { command.execute(it) }
        }
        return current
    }
}
