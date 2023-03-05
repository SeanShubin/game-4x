package com.seanshubin.game_4x.prototype2

import arrow.core.Either
import arrow.core.left
import arrow.core.right

class CommandFactoryImpl:CommandFactory {
    override fun build(name: String, parameters:List<Item>): Either<String, Command> =
        when(name){
            "add" -> buildAdd(parameters)
            "remove" -> buildRemove(parameters)
            else -> Messages.commandNotFound(name).left()
        }

    private fun buildAdd(parameters: List<Item>):Either<String, Command> {
        val item = parameters.getOrNull(0) ?: return Messages.missingParameter(0).left()
        return AddCommand(item).right()
    }
    private fun buildRemove(parameters: List<Item>):Either<String, Command> {
        val item = parameters.getOrNull(0) ?: return Messages.missingParameter(0).left()
        return RemoveCommand(item).right()
    }
}
