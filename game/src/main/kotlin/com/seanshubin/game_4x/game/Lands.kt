package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateFirstApplicable

data class Lands(val landList: List<Land> = emptyList()) {
    fun addLand(land:Land):Lands = copy(landList = landList + land)
    fun setWithResources(landCount:Int, resources:Resources):Lands{
        val newLandList = (1..landCount).map { Land(resources =resources) }
        return copy(landList = newLandList)
    }
    fun claimAndBuild(resourceName:String):Lands? {
        val newLandList = landList.updateFirstApplicable {
            it.claimAndBuild(resourceName)
        } ?: return null
        return copy(landList = newLandList)
    }
    fun toObject():List<Any> = landList.map{it.toObject()}
}
