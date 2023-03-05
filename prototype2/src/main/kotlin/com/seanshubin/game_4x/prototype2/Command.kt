package com.seanshubin.game_4x.prototype2

interface Command {
    fun execute(state: Items):Result
    fun toMessage():String
}
