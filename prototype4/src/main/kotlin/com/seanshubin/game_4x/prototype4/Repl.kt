package com.seanshubin.game_4x.prototype4

class Repl(
    private val readLine: () -> String?,
    private val writeLine: (String) -> Unit,
    private val interpreter: Interpreter,
    private val initialState: Items
) : Runnable {
    override fun run() {
        var line = readLine()
        var state = initialState
        while (line != null) {
            val result = interpreter.execute(state, line)
            result.lines.forEach(writeLine)
            state = result.state
            line = readLine()
        }
    }
}
