package com.seanshubin.game_4x.prototype4

interface Environment {
    fun load(name: String): Result<Unit>
}