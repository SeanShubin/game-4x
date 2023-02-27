package com.seanshubin.game_4x.command

import com.seanshubin.game_4x.format.JsonMappers

object DebugCommand {
    fun debug(command:LandCommand){
        val name = command.javaClass.simpleName
        val obj = command.toObject()
        val compact = JsonMappers.compact.writeValueAsString(obj)
        val message = "$name $compact"
        println(message)
    }
}