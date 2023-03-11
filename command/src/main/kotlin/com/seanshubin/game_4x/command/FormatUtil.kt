package com.seanshubin.game_4x.command

object FormatUtil {
    fun List<String>.indent(): List<String> = map { "  $it" }
}
