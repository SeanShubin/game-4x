package com.seanshubin.game_4x.prototype2

class InterpreterImpl(
    private val commandFactory: CommandFactory
) : Interpreter {
    override fun execute(state:Items, text: String): Result {
        val call = Format.parseCall(text) ?: return Result.invalidSyntax(state, text)
        val command = commandFactory.lookup(call.name) ?: return Result.commandNotFound(state,call.name)
        return command.execute(state, call.parameters)
    }
}
