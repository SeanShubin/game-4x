package com.seanshubin.game_4x.game

data class Land(
    val claimed:Boolean,
    val resources:Resources
) {
    fun claimAndBuild(resourceName:String):Land? = claim()?.build(resourceName)
    fun setResources(resources:Resources):Land = copy(resources =resources)
    private fun claim():Land? = if(claimed) null else copy(claimed = true)
    private fun build(resourceName:String):Land? {
        val newResources = resources.build(resourceName)
        return if(newResources == null) null else (copy(resources = newResources))
    }
    fun toObject():Map<String, Any> = mapOf(
        "claimed" to claimed,
        "resources" to resources.toObject()
    )
    fun addResource(resource:Resource) = copy(resources = resources.add(resource))
    companion object {
        val empty = Land(claimed = false, resources = Resources.empty)
    }
}
