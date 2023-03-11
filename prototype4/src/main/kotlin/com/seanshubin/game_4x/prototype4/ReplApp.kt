package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.prototype4.assembler.Assembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerImpl

object ReplApp {
    @JvmStatic
    fun main(args: Array<String>) {
        val assemblerMap = CommandAssemblers.assemblerMap
        val assembler:Assembler = AssemblerImpl(assemblerMap)
        val parser:Parser = ParserImpl(assembler)
        val commandLookup:CommandLookup = CommandLookupImpl()
        val interpreter:Interpreter = InterpreterImpl(parser, commandLookup)
        val state = Items()
        val repl = Repl(::readLine, ::println, interpreter, state)
        repl.run()
    }
}
