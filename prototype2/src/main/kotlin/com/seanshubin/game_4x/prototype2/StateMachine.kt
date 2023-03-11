package com.seanshubin.game_4x.prototype2

interface StateMachine {
    val builder: Builder
    fun processLine(line: Line): StateMachine
}
