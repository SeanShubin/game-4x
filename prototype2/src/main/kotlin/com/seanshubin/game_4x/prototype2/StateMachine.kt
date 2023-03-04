package com.seanshubin.game_4x.prototype2

interface StateMachine {
    val commandScriptBuilder:CommandScriptBuilder
    fun processLine(line: Line): StateMachine
}
