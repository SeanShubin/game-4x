package com.seanshubin.game_4x.prototype4

class CommandLookupImpl:CommandLookup {
    override fun lookupByName(name: String): Command =
        when(name){
            "add" -> AddCommand
            else -> UnknownCommand(name)
        }
}