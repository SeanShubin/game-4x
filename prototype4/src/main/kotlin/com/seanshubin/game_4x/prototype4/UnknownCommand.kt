package com.seanshubin.game_4x.prototype4

data class UnknownCommand(val name:String):Command {
    override fun execute(state: Items, parameters: Parameters): CommandResult =
        CommandResult(success = false, state, listOf(
            "Unknown command '$name"
        ))
}
