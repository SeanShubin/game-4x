package com.seanshubin.game_4x.prototype4

data class UnknownCommand(val name: String, val commandNames: List<String>) : Command {
    override fun execute(state: Items, parameters: Parameters, environment: Environment): CommandResult =
        CommandResult(success = false, state, listOf(
            "Unknown command '$name', supported commands are"
        ) + commandNames.map { "  $it" })
}
