package com.seanshubin.game_4x.prototype4

object RemoveCommand:Command {
    override fun execute(state: Items, parameters: Parameters, environment:Environment): CommandResult {
        val validated = parameters
            .requireAtLeastCount(1)
            .requireIsItem(0)
            .requireIsInt(1)
        if(validated.notValid()) return CommandResult(success=false, state,validated.messages)
        val item = validated.itemAt(0)
        val quantity = validated.intOrDefaultAt(1, 1)
        val newState = state.removeOrNull(item, quantity)
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
