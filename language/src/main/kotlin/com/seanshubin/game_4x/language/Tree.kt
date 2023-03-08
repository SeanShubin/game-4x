package com.seanshubin.game_4x.language

interface Tree<T> {
    fun toList(): List<T>
    fun toLines(): List<String>
}