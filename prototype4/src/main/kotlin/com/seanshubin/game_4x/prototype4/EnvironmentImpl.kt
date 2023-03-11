package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.contract.FilesContract
import java.nio.file.Path

class EnvironmentImpl(
    private val loadDir: Path,
    private val files: FilesContract,
    private val lineSource:LineSource
):Environment {
    override fun load(name: String):Result<Unit> {
        val file = loadDir.resolve("$name.txt")
        return if(files.exists(file)){
            val lines = files.readAllLines(file)
            lineSource.putLines(lines)
            val message = "Loaded ${lines.size} lines from $file"
            Result(success = true, Unit, listOf(message))
        } else {
            val message = "File $file not found"
            Result(success = false, Unit, listOf(message))
        }
    }
}
