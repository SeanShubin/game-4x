package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Universe

data class UniverseFailure(val command: UniverseCommand, val universe: Universe, val message: String) : HasToObject {
    override fun toString(): String =
        JsonMappers.pretty.writeValueAsString(toObject())

    override fun toObject(): Map<String, Any> = mapOf(
        "command" to command.toObject(),
        "universe" to universe.toObject(),
        "message" to message
    )
}
