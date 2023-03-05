package com.seanshubin.game_4x.prototype2

object AddCommand:Command {
    override fun execute(state: Items, parameters: List<Item>): Result {
        val item = parameters.getOrNull(0) ?: return Result.missingParameter(state,0)
        val newState = state.add(item)
        val oldQuantity = state.count(item)
        val newQuantity = newState.count(item)
        val messages = Messages.quantityChanged(oldQuantity, newQuantity, item)
        return Result.success(newState, messages)
    }
}
