package com.seanshubin.game_4x.skirmish

data class GameUnit(val attack: Int, val armor: Int) {
    fun fireVolley(): Iterable<Int> = listOf(attack)
    fun isDestroyedBy(hit: Int): Boolean = hit >= armor
}