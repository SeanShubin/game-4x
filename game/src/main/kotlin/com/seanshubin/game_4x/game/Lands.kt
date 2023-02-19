package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.indexOfFirstOrNull
import com.seanshubin.game_4x.game.ListUtil.updateAtIndex
import com.seanshubin.game_4x.game.ListUtil.updateFirstApplicable

data class Lands(val landList: List<Land>) {
    fun addLand(land:Land):Lands = copy(landList = landList + land)
    fun setWithResources(landCount:Int, resources:Resources):Lands{
        val newLandList = (1..landCount).map { Land.empty.setResources(resources) }
        return copy(landList = newLandList)
    }
    fun claimAndBuild(resourceName:String):Lands? {
        val newLandList = landList.updateFirstApplicable {
            it.claimAndBuild(resourceName)
        } ?: return null
        return copy(landList = newLandList)
    }
    fun claimedExists(): Boolean = landList.any { it.claimed }
    fun nonClaimedExists(): Boolean = landList.any { !it.claimed }
    fun toObject():List<Any> = landList.map{it.toObject()}

    companion object {
        val empty = Lands(landList = emptyList())
    }
}
