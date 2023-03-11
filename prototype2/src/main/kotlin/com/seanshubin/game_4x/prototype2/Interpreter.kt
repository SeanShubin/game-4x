package com.seanshubin.game_4x.prototype2

interface Interpreter {
    fun execute(state: Items, text: String): Result
}