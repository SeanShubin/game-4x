package com.seanshubin.game_4x.domain

data class Army(val units: List<GameUnit>) {
    constructor(vararg units:GameUnit):this(units.toList())
    fun isEmpty(): Boolean = units.isEmpty()
    fun fireVolley(): List<Int> = units.flatMap { it.fireVolley() }
    tailrec fun receiveVolley(volley: List<Int>): Army =
            when {
                isEmpty() -> this
                volley.isEmpty() -> this
                topUnit.isDestroyedBy(volley[0]) -> destroyTopUnit()
                else -> receiveVolley(volley.subList(1, volley.size))
            }
    private val topUnit get() = units[0]
    private fun destroyTopUnit():Army = copy(units = units.subList(1, units.size))
}
