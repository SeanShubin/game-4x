package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.command.FormatUtil.indent
import com.seanshubin.game_4x.format.JsonMappers
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Universe

data class UniverseSuccess(
    val name: String,
    val universe: Universe,
    val message: String,
    val children: List<UniverseSuccess>
) : HasToObject {
    override fun toString(): String =
        JsonMappers.pretty.writeValueAsString(toObject())

    override fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "universe" to universe.toObject(),
        "message" to message,
        "children" to children
    )

    fun toLines(): List<String> {
        val parentLine = "$name: $message"
        val childLines = children.flatMap { it.toLines() }
        return listOf(parentLine) + childLines.indent()
    }
}
