package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.applyToExactlyOneApplicable
import com.seanshubin.game_4x.game.ListUtil.indexOfFirstOrNull
import com.seanshubin.game_4x.game.ListUtil.replaceAtIndex

data class Resources(val resourceList:List<Resource> = emptyList()){
    fun generate(resourceName:String):Resources? {
        val newResourceList = resourceList.applyToExactlyOneApplicable({it.name == resourceName}){
            it.generate()
        } ?: return null
        return copy(resourceList = newResourceList)
    }
    fun build(resourceName:String):Resources? {
        val index = resourceList.indexOfFirstOrNull{it.name == resourceName} ?: return null
        val newResource = resourceList[index].buildGatherer()
        return if(newResource == null) null else copy(resourceList = resourceList.replaceAtIndex(index, newResource))
    }
    fun add(resource:Resource):Resources = copy(resourceList = resourceList + resource)
    fun toObject():List<Any> = resourceList.map { it.toObject() }
    companion object {
        val empty = Resources(emptyList())
    }
}
