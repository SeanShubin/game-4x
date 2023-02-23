package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.applyToExactlyOneApplicable

data class Resources(val resourceList: List<Resource> = emptyList()) {
    val resourceMap = resourceList.associateBy { it.name }
    fun getNames(): List<String> = resourceList.map { it.name }
    fun setAtLocation(resourceName: String, resourceLocation: ResourceLocation, newValue: Int): Resources =
        updateResource(resourceName) { resource ->
            resource.setValueAtLocation(resourceLocation, newValue)
        }

    fun addResource(resourceName: String, prevalence: Int, density: Int): Resources {
        if (resourceMap.contains(resourceName)) throw RuntimeException("Resource $resourceName already exists")
        val resource = Resource(
            name = resourceName,
            prevalence = prevalence,
            density = density
        )
        return copy(resourceList = resourceList + resource)
    }

    fun updateResource(name: String, update: (Resource) -> Resource): Resources =
        copy(resourceList.applyToExactlyOneApplicable(Resource.nameMatches(name), update))

    fun toObject(): List<Any> = resourceList.map { it.toObject() }
}
