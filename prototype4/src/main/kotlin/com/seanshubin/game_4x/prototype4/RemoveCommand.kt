package com.seanshubin.game_4x.prototype4

object RemoveCommand:Command {
    override fun execute(state: Items, parameters: Parameters): CommandResult {
        val validated = parameters.requireCount(1).requireIsItem(0)
        if(validated.notValid()) return CommandResult(success=false, state,validated.messages)
        val item = validated.itemAt(0)
        val newState = state.removeOrNull(item)
        val oldCount = state.count(item)
        val itemString = Format.formatItem(item)
        return if(newState == null){
            val message = listOf("Unable to remove item $itemString, count is $oldCount")
            CommandResult(success = false, state, message)
        } else {
            val newCount = newState.count(item)
            val message = listOf("$oldCount -> $newCount $itemString changed quantity")
            CommandResult(success = true, newState, message)
        }
    }
}
