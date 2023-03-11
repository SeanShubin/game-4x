package com.seanshubin.game_4x.prototype4

interface Command {
    fun execute(state:Items, parameters:Parameters, environment:Environment):CommandResult
}
