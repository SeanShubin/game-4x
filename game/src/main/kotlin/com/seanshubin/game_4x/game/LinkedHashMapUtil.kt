package com.seanshubin.game_4x.game

object LinkedHashMapUtil {
    operator fun <A,B> LinkedHashMap<A, B>.plus(entry:Pair<A,B>):LinkedHashMap<A,B> {
        @Suppress("UNCHECKED_CAST")
        val clone = this.clone() as LinkedHashMap<A, B>
        clone[entry.first] = entry.second
        return clone
    }
}
