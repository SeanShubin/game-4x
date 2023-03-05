package com.seanshubin.game_4x.prototype2

data class OptionalCommand(val command:Command):Command {
    override fun execute(state: Items): Result {
        val result = command.execute(state)
        return if(result.success){
            result
        } else {
            result.copy(success= true)
        }
    }

    override fun toMessage(): String = "optional ${command.toMessage()}"
}