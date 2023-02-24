package com.seanshubin.game_4x.game

data class Land(val resources: List<Resource> = emptyList()) {
    val resourceByName = resources.associateBy { it.name }
    fun resourceExists(name: String) = resourceByName.containsKey(name)
    fun addResource(name: String, prevalence: Int, density: Int): Land =
        copy(resources = resources + Resource(name, prevalence, density))
}
