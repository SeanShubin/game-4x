package com.seanshubin.game_4x.prototype2

data class Script(
    val inputNameList: List<String>,
    val topCommandList: List<TopCommand>,
    val subCommandList: List<SubCommand>,
    val exampleList: List<Example>
) {
    fun toObject(): Map<String, Any> = mapOf(
        "inputNameList" to inputNameList,
        "topCommandList" to topCommandList.map { it.toObject() },
        "subCommandList" to subCommandList.map { it.toObject() },
        "exampleList" to exampleList.map { it.toObject() }
    )
}
