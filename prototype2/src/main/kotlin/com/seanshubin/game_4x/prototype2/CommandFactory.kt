package com.seanshubin.game_4x.prototype2

interface CommandFactory {
    fun lookup(name:String):Command?
}