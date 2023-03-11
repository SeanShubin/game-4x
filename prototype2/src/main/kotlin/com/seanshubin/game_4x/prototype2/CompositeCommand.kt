package com.seanshubin.game_4x.prototype2

data class CompositeCommand(val commands: List<Command>) : Command {
    override fun execute(state: Items): Result {
        var currentState = state
        val allMessages = mutableListOf<String>()
        commands.forEach { command ->
            val result = command.execute(currentState)
            allMessages.add(command.toMessage())
            allMessages.addAll(result.messages.map { "  $it" })
            if (result.success) {
                currentState = result.state
            } else {
                return result.copy(messages = allMessages)
            }
        }
        return Result.success(currentState, allMessages)
    }

    override fun toMessage(): String = commands.joinToString(", ", "composite ") { it.toMessage() }

    companion object {
        fun fromList(list: List<Command>): Command = if (list.size == 1) {
            list[0]
        } else {
            CompositeCommand(list)
        }
    }
}
