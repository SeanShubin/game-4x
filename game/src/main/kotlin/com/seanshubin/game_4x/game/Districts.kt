package com.seanshubin.game_4x.game

data class Districts(val districtList:List<District>){
    constructor(resources: Resources):this(resources.resourceList.map{District(it)})
    fun fullyDeveloped():Boolean = districtList.all { it.developed }
    fun toObject():List<Any> = districtList.map { it.toObject() }
}
