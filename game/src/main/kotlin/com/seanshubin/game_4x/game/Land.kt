package com.seanshubin.game_4x.game

data class Land(
    val claimed:Boolean = false,
    val resources:Resources = Resources(),
    val labor:Int = 0
) {
    fun claimAndBuild(resourceName:String):Land? = claim()?.build(resourceName)
    private fun claim():Land? = if(claimed) null else copy(claimed = true)
    private fun build(resourceName:String):Land? {
        val newResources = resources.build(resourceName)
        return if(newResources == null) null else (copy(resources = newResources))
    }
    fun toObject():Map<String, Any> = mapOf(
        "claimed" to claimed,
        "resources" to resources.toObject(),
        "labor" to labor
    )
}
