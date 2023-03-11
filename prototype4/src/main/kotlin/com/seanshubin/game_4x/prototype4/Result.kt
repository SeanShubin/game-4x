package com.seanshubin.game_4x.prototype4

data class Result<T>(
    val success: Boolean,
    val value: T,
    val messages: List<String>
)
