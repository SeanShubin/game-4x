package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.prototype4.assembler.AssemblerImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class ReplTest {
    @Test
    fun addColonizer(){
        // given
        val input = listOf("add {colonizer}")
        val expected = listOf("0 -> 1 {colonizer} changed quantity")
        val tester = Tester(input)

        // when
        tester.repl.run()

        // then
        val actual = tester.writeLine.linesWritten
        assertEquals(expected, actual)
    }

    class Tester(input:List<String>){
        val readLine = ReadLineStub(input)
        val writeLine= WriteLineStub()
        val assemblerMap = CommandAssemblers.assemblerMap
        val assembler = AssemblerImpl(assemblerMap)
        val parser = ParserImpl(assembler)
        val commandLookup = CommandLookupImpl()
        val interpreter = InterpreterImpl(parser, commandLookup)
        val state = Items()
        val repl = Repl(readLine, writeLine, interpreter, state)
    }

    class ReadLineStub(val lines:List<String>):(() -> String?){
        var index = 0
        override fun invoke(): String? {
            val line = lines.getOrNull(index)
            index++
            return line
        }
    }

    class WriteLineStub:(String)->Unit {
        private val mutableLinesWritten = mutableListOf<String>()
        val linesWritten:List<String> = mutableLinesWritten
        override fun invoke(line: String) {
            mutableLinesWritten.add(line)
        }
    }
}