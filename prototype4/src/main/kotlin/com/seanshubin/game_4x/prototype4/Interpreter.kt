package com.seanshubin.game_4x.prototype4

interface Interpreter {
    fun execute(state:Items, line:String):CommandResult
}
