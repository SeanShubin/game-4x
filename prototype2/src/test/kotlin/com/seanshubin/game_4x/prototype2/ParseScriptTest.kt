package com.seanshubin.game_4x.prototype2

import com.seanshubin.game_4x.format.JsonMappers
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.Test

class ParseScriptTest {
    @Test
    fun parseAllScripts() {
        val scriptDirName = javaClass.name.split('.').dropLast(1).joinToString("/", "src/main/resources/")
        val scriptDir = Paths.get(scriptDirName)
        val files = Files.list(scriptDir)
        files.forEach(::parseScript)
    }

    @Test
    fun parseOperateGathererScript() {
        val pathFromSourceRoot = "/com/seanshubin/game_4x/prototype2/operate-gatherer.txt"
        val inputStream = this.javaClass.getResourceAsStream(pathFromSourceRoot)
            ?: throw RuntimeException("Resource not found: $pathFromSourceRoot")
        val text = String(inputStream.readAllBytes())
        val plainLines = text.split(Format.lineBreak)
        val lines = plainLines.mapIndexed { index, line ->
            Line(pathFromSourceRoot, index, line)
        }
        val scriptParser = ScriptParserImpl()
        val script = scriptParser.parseLines(lines)

        println(JsonMappers.pretty.writeValueAsString(script.toObject()))
    }

    fun parseScript(file: Path) {
        val plainLines = Files.readAllLines(file)
        val lines = plainLines.mapIndexed { index, line ->
            Line(file.toString(), index, line)
        }
        val scriptParser = ScriptParserImpl()
        val script = scriptParser.parseLines(lines)
        println(JsonMappers.pretty.writeValueAsString(script.toObject()))
    }
}