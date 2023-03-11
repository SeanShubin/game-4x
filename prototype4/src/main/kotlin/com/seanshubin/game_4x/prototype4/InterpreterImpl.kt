package com.seanshubin.game_4x.prototype4

class InterpreterImpl(
    private val parser: Parser,
    private val commandLookup: CommandLookup,
    private val environment: Environment
) : Interpreter {
    override fun execute(state: Items, line: String): CommandResult =
        when (val parseResult = parser.parseCall(line)) {
            is ParseResult.Success -> {
                val call = parseResult.call
                val command = commandLookup.lookupByName(call.name)
                val parameters = Parameters(call.parameters)
                val commandResult = command.execute(state, parameters, environment)
                commandResult
            }
            is ParseResult.Failure -> {
                CommandResult(success = false, state, parseResult.messages)
            }
        }
}
