package com.seanshubin.game_4x.game

data class Land(
    val claimed:Boolean = false,
    val resources:Resources = Resources(),
    val labor:Int = 0
) {
    fun endTurn():Land = copy(labor = 0, resources = resources.endTurn())
    fun claimAndBuild(resourceName:String):Land? = claim()?.build(resourceName)
    fun generate(resourceName:String):Land? {
        val newResources = resources.generate(resourceName)
        return if(newResources == null) null else copy(resources = newResources)
    }
    fun generateLabor():Land? = consumeResource(Names.FOOD)?.produceLabor()
    private fun consumeLabor():Land? = if(labor > 0) copy(labor = labor-1) else null
    private fun produceGatherer(resourceName:String):Land? {
        val newResources = resources.build(resourceName)
        return if(newResources== null) null else copy(resources = newResources)
    }
    fun buildGatherer(resourceName:String):Land? = consumeLabor()?.produceGatherer(resourceName)
    private fun consumeResource(resourceName:String):Land? {
        val newResources = resources.consumeFromSurface(resourceName) ?: return null
        return copy(resources = newResources)
    }
    private fun produceLabor():Land = copy(labor = labor+1)
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
