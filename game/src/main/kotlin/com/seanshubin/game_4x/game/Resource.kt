package com.seanshubin.game_4x.game

data class Resource(
    val name: String,
    val density: Int,
    val activated:Boolean = false
):Thing
