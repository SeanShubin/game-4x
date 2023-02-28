package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class CompositeUniverseCommand(val list: List<UniverseCommand>) : UniverseCommand {
    constructor(vararg universeCommand: UniverseCommand) : this(universeCommand.toList())

    override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
        val details = mutableListOf<String>("composite universe command ${list.size}")
        var current: Either<UniverseFailure, UniverseSuccess> = UniverseSuccess(
            this,
            universe,
            emptyList()
        ).right()
        list.forEach { command ->
            current = current.flatMap {
                command.execute(it.universe)
            }
            when(val x = current){
                is Either.Right -> details.addAll(x.value.details)
                else -> {}
            }
        }
        return current
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
