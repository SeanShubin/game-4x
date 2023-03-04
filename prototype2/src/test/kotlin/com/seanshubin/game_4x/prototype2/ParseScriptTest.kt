package com.seanshubin.game_4x.prototype2

import com.seanshubin.game_4x.format.JsonMappers
import kotlin.test.Test

class ParseScriptTest {
    @Test
    fun parseOperateGathererScript(){
        val pathFromSourceRoot = "/com/seanshubin/game_4x/prototype2/operate-gatherer.txt"
        val inputStream = this.javaClass.getResourceAsStream(pathFromSourceRoot) ?: throw RuntimeException("Resource not found: $pathFromSourceRoot")
        val text = String(inputStream.readAllBytes())
        val plainLines = text.split(Parser.lineBreak)
        val lines = plainLines.mapIndexed{ index, line ->
            Line(pathFromSourceRoot, index, line)
        }
        val commandScriptBuilder = CommandScriptBuilder()
        val initialState = State.Companion.ReadingHeader
        val initialStateMachine = StateMachineImpl(initialState, commandScriptBuilder)
        val finalStateMachine = lines.fold(initialStateMachine, StateMachine::processLine)
        val finalPartial = finalStateMachine.commandScriptBuilder
        val commandScript = finalPartial.build()

        println(JsonMappers.pretty.writeValueAsString(commandScript.toObject()))
    }
}