package com.seanshubin.game_4x.prototype4

object ListAllCommand:Command {
    override fun execute(state: Items, parameters: Parameters, environment:Environment): CommandResult {
        val validated = parameters.requireAtLeastCount(0)
        if(validated.notValid()) return CommandResult(success=false, state,validated.messages)
        val itemMessages = state.itemMap.map(::composeMessage)
        val messages = itemMessages.ifEmpty { listOf("<empty>" ) }
        return CommandResult(success = true, state, messages)
    }

    private fun composeMessage(entry:Map.Entry<Item, Int>):String {
        val (item, quantity) = entry
        val itemString = Format.formatItem(item)
        return "$quantity of $itemString"
    }
}
