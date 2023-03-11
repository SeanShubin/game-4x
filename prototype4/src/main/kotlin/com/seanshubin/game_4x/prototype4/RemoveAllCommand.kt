package com.seanshubin.game_4x.prototype4

object RemoveAllCommand:Command {
    override fun execute(state: Items, parameters: Parameters): CommandResult {
        val newState = Items()
        val messages = listOf("removed all items")
        return CommandResult(success = true, newState, messages)
    }
}
