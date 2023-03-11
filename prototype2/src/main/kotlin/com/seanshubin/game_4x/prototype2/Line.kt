package com.seanshubin.game_4x.prototype2

data class Line(val source: String, val index: Int, val text: String) {
    override fun toString(): String = "$source[${index + 1}]: $text"

    companion object {
        fun fromPlainLines(source: String, plainLines: List<String>): List<Line> {
            val lines = plainLines.mapIndexed { index, line ->
                Line(source, index, line)
            }
            return lines
        }
    }
}
