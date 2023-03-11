package com.seanshubin.game_4x.prototype2

data class AddCommand(val item: Item) : Command {
    override fun execute(state: Items): Result {
        val newState = state.add(item)
        val oldQuantity = state.count(item)
        val newQuantity = newState.count(item)
        val messages = Messages.quantityChanged(oldQuantity, newQuantity, item)
        return Result.success(newState, messages)
    }

    override fun toMessage(): String = "add ${Format.formatItem(item)}"
}
