package com.seanshubin.game_4x.prototype4

object LoadCommand:Command {
    override fun execute(state: Items, parameters: Parameters, environment:Environment): CommandResult {
        val validated = parameters
            .requireAtLeastCount(1)
            .requireIsString(0)
        if(validated.notValid()) return CommandResult(success=false, state,validated.messages)
        val name = validated.stringAt(0)
        val result = environment.load(name)
        return if(result.success) {
            CommandResult(success = true, state, result.messages)
        } else {
            CommandResult(success = false, state, result.messages)
        }
    }
}
