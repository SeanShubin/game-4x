package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Universe

data class UniverseSuccess(val command: UniverseCommand, val universe: Universe, val details: List<String>) : HasToObject {
    override fun toString(): String =
        JsonMappers.pretty.writeValueAsString(toObject())

    override fun toObject(): Map<String, Any> = mapOf(
        "command" to command.toObject(),
        "universe" to universe.toObject(),
        "details" to details
    )

    fun toLines():List<String> {
        if(details.isEmpty()) return emptyList()
        val compact = JsonMappers.compact.writeValueAsString(command.toObject())
        return listOf(compact) + details.indent()
    }
}
