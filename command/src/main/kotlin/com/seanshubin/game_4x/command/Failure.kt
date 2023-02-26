package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.HasToObject

data class Failure(val context: HasToObject, val state: HasToObject, val message: String) : HasToObject {
    override fun toString(): String =
        JsonMappers.pretty.writeValueAsString(toObject())

    override fun toObject(): Map<String, Any> = mapOf(
        "contextType" to context.javaClass.simpleName,
        "context" to context.toObject(),
        "stateType" to state.javaClass.simpleName,
        "state" to state.toObject(),
        "message" to message
    )
}