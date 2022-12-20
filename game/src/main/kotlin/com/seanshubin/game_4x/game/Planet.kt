package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.LinkedHashMapUtil.plus

data class Planet(
    val name: String,
    val size: Int,
    val resources: LinkedHashMap<String, Resource> = LinkedHashMap(),
    val inOrbit:List<String> = emptyList()
) {
    fun addResource(resource: Resource): Planet {
        val newResources = resources + Pair(resource.name, resource)
        return copy(resources = newResources)
    }

    fun addOrbital(name:String):Planet {
        return copy(inOrbit = inOrbit + name)
    }
}
