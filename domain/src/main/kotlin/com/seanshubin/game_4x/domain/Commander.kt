package com.seanshubin.game_4x.domain

data class Commander(val name:String, val army:Army, val intent:Intent) {
    fun fireVolley(): List<Int> = army.fireVolley()

    fun receiveVolley(volley: List<Int>): Commander = copy(army = army.receiveVolley(volley))
}
