package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.right
import com.seanshubin.game_4x.game.Universe

data class CompositeUniverseCommand(val parent:UniverseCommand, val list: List<UniverseCommand>) : UniverseCommand {
//    constructor(vararg universeCommand: UniverseCommand) : this(universeCommand.toList())

    override fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess> {
        val children = mutableListOf<UniverseSuccess>()
        var current = universe
        list.forEach { command ->
            val result = command.execute(current)
            when(result){
                is Either.Right -> {
                    children.add(result.value)
                    current = result.value.universe
                }
                is Either.Left -> {
                    return result
                }
            }
        }
        return UniverseSuccess(parent.javaClass.simpleName, current, "count: ${list.size}", children).right()
    }

    override fun toObject(): Map<String, Any> = mapOf(
        "list" to list.map { it.toObject() }
    )
}
