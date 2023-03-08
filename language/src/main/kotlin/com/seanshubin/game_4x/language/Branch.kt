package com.seanshubin.game_4x.language

data class Branch<T>(val name: String, val list: List<Tree<T>>) : Tree<T> {
    override fun toList(): List<T> = list.flatMap { it.toList() }
    override fun toLines(): List<String> = listOf(name) + list.flatMap { it.toLines() }.map { "  $it" }
}
