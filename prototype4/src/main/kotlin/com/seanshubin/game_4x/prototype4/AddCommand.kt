package com.seanshubin.game_4x.prototype4

object AddCommand:Command {
    override fun execute(state: Items, parameters: Parameters): CommandResult {
        val validated = parameters.requireCount(1).requireIsItem(0)
        if(validated.notValid()) return CommandResult(success=false, state,validated.messages)
        val item = validated.itemAt(0)
        val newState = state.add(item)
        val oldCount = state.count(item)
        val newCount = newState.count(item)
        val itemString = Format.formatItem(item)
        val message = listOf("$oldCount -> $newCount $itemString changed quantity")
        return CommandResult(success = true, newState, message)
    }
}
