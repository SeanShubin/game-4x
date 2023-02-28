package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Land

data class LandSuccess(val command: LandCommand, val land: Land, val message: String) : HasToObject {
    override fun toString(): String =
        JsonMappers.pretty.writeValueAsString(toObject())

    override fun toObject(): Map<String, Any> = mapOf(
        "command" to command.toObject(),
        "land" to land.toObject(),
        "message" to message
    )
}
