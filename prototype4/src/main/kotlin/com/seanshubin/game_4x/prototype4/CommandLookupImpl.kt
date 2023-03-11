package com.seanshubin.game_4x.prototype4

class CommandLookupImpl:CommandLookup {
    private val commandMap = mapOf(
        "add" to AddCommand,
        "remove" to RemoveCommand,
        "list-all" to ListAllCommand,
        "remove-all" to RemoveAllCommand
    )
    private val commandNames = commandMap.map{it.key}.sorted()
    override fun lookupByName(name: String): Command =
        commandMap[name] ?:  UnknownCommand(name, commandNames)
}
