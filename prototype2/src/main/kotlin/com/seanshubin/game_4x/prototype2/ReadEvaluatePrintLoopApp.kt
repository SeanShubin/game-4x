package com.seanshubin.game_4x.prototype2

object ReadEvaluatePrintLoopApp {
    @JvmStatic
    fun main(args: Array<String>) {
        // given
        val commandFactory:CommandFactory = CommandFactoryImpl()
        val interpreter:Interpreter = InterpreterImpl(commandFactory)
        println("Waiting for input...")
        var line = readLine()!!
        var state = Items()
        while(line != "exit"){
            val result = interpreter.execute(state, line)
            state = result.state
            result.messages.forEach(::println)
            line = readLine()!!
        }
    }
}
