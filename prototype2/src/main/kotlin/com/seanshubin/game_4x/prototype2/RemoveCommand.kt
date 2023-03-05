package com.seanshubin.game_4x.prototype2

data class RemoveCommand(val item:Item):Command {
    override fun execute(state: Items): Result {
        val newState = state.removeOrNull(item)
        return if(newState == null){
            val message = "unable to remove ${Format.formatItem(item)} from ${Format.formatItems(state)}"
            val messages = listOf(message)
            Result.failure(state, messages)
        } else {
            val oldQuantity = state.count(item)
            val newQuantity = newState.count(item)
            val message = "$oldQuantity -> $newQuantity changed quantity of ${Format.formatItem(item)}"
            val messages = listOf(message)
            Result.success(newState, messages)
        }
    }

    override fun toMessage(): String = "remove ${Format.formatItem(item)}"
}
