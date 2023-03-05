package com.seanshubin.game_4x.prototype2

import com.seanshubin.game_4x.format.JsonMappers
import kotlin.test.Test

class ParseScriptTest {
    @Test
    fun parseOperateGathererScript(){
        val pathFromSourceRoot = "/com/seanshubin/game_4x/prototype2/operate-gatherer.txt"
        val inputStream = this.javaClass.getResourceAsStream(pathFromSourceRoot) ?: throw RuntimeException("Resource not found: $pathFromSourceRoot")
        val text = String(inputStream.readAllBytes())
        val plainLines = text.split(Format.lineBreak)
        val lines = plainLines.mapIndexed{ index, line ->
            Line(pathFromSourceRoot, index, line)
        }
        val scriptParser = ScriptParserImpl()
        val script = scriptParser.parseLines(lines)

        println(JsonMappers.pretty.writeValueAsString(script.toObject()))
    }
}