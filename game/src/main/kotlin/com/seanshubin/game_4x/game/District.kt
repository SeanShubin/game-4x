package com.seanshubin.game_4x.game

data class District(val developed: Boolean, val resource: Resource) {
    constructor(resource: Resource) : this(developed = false, resource = resource)
    fun toObject():Map<String, Any> = mapOf(
        "developed" to developed,
        "resource" to resource.toObject()
    )
}
