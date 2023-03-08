package com.seanshubin.game_4x.language

data class Leaf<T>(val name: String, val value: T) : Tree<T> {
    override fun toList(): List<T> = listOf(value)
    override fun toLines(): List<String> = listOf("$name: $value")
}
