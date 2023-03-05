package com.seanshubin.game_4x.prototype2

import arrow.core.Either

class InterpreterImpl(
    private val commandFactory: CommandFactory
) : Interpreter {
    override fun execute(state:Items, text: String): Result {
        val call = Format.parseCall(text) ?: return Result.invalidSyntax(state, text)
        val messageOrCommand = commandFactory.build(call.name, call.parameters)
        val command = when (messageOrCommand) {
            is Either.Right -> messageOrCommand.value
            is Either.Left -> return Result.failure(state, listOf(messageOrCommand.value))
        }
        return command.execute(state)
    }
}
