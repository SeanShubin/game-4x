package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateAtIndex

data class Lands(val landList: List<Land>) {
    fun claimLand(landIndex: Int): Lands {
        val newLandList = landList.updateAtIndex(landIndex) { land ->
            land.claim()
        }
        return copy(landList = newLandList)
    }

    fun claimedExists(): Boolean = landList.any { it.claimed }
    fun nonClaimedExists(): Boolean = landList.any { !it.claimed }
    fun fullyDeveloped(): Boolean = landList.all { it.fullyDeveloped() }
    fun toObject():List<Any> = landList.map{it.toObject()}

    constructor(size: Int, resources: Resources) : this((0 until size).map {
        Land(resources)
    })
}
