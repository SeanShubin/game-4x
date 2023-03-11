package com.seanshubin.game_4x.prototype4

data class CommandResult(
    val success: Boolean,
    val state: Items,
    val lines: List<String>
)
