package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.contract.FilesContract
import com.seanshubin.game_4x.contract.FilesDelegate
import com.seanshubin.game_4x.prototype4.assembler.Assembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerImpl
import java.nio.file.Path
import java.nio.file.Paths

object ReplApp {
    @JvmStatic
    fun main(args: Array<String>) {
        val assemblerMap = CommandAssemblers.assemblerMap
        val assembler:Assembler = AssemblerImpl(assemblerMap)
        val parser:Parser = ParserImpl(assembler)
        val commandLookup:CommandLookup = CommandLookupImpl()
        val lineSource:LineSource = LineSourceImpl(::readLine)
        val files: FilesContract = FilesDelegate
        val loadDir: Path = Paths.get("script")
        val environment:Environment = EnvironmentImpl(loadDir, files, lineSource)
        val interpreter:Interpreter = InterpreterImpl(parser, commandLookup, environment)
        val state = Items()
        val repl = Repl(lineSource::readLine, ::println, interpreter, state)
        println("Waiting for commands")
        repl.run()
    }
}
