package com.seanshubin.game_4x.game

data class Land(
    val security: Int = 0,
    val resources: Resources = Resources(),
    val orbit: Orbit = Orbit()
) {
    fun updateResource(resourceName: String, update: (Resource) -> Resource): Land =
        copy(resources = resources.updateResource(resourceName, update))

    fun setSecurity(newSecurity: Int): Land = copy(security = newSecurity)
    fun addOrbital(name: String): Land = copy(orbit = orbit.put(name))
    fun takeOrbital(name: String): Land = copy(orbit = orbit.take(name))
    fun addResource(resourceName: String, prevalence: Int, density: Int): Land =
        copy(resources = resources.addResource(resourceName, prevalence, density))

    fun resource(resourceName: String): Resource = resources.resourceMap.getValue(resourceName)
    fun setResourceAtLocation(resourceName: String, resourceLocation: ResourceLocation, newValue: Int): Land =
        copy(resources = resources.setAtLocation(resourceName, resourceLocation, newValue))

    fun toObject(): Map<String, Any> = mapOf(
        "security" to security,
        "resources" to resources.toObject(),
        "orbit" to orbit.toObject()
    )
}
