package com.seanshubin.game_4x.prototype2

class CommandFactoryImpl:CommandFactory {
    override fun lookup(name: String): Command? =
        when(name){
            "add" -> AddCommand
            "remove" -> RemoveCommand
            else -> null
        }
}
