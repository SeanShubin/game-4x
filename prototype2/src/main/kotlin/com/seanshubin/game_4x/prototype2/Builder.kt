package com.seanshubin.game_4x.prototype2

data class Builder(
    val inputNameList: List<String> = emptyList(),
    val topCommandList: List<TopCommand> = emptyList(),
    val subCommandName: String? = null,
    val subCommandCallList: List<SubCommandCall> = emptyList(),
    val subCommandList: List<SubCommand> = emptyList(),
    val exampleParameterList: List<Pair<String, Any>> = emptyList(),
    val exampleStateBeforeItemList: List<Item> = emptyList(),
    val exampleLogMessageList: List<String> = emptyList(),
    val exampleStateAfterItemList: List<Item> = emptyList(),
    val exampleList: List<Example> = emptyList()
) {
    fun input(name: String): Builder =
        copy(inputNameList = inputNameList + name)

    fun topCommand(topCommand: TopCommand): Builder =
        copy(topCommandList = topCommandList + topCommand)

    fun subCommandName(name: String): Builder =
        copy(subCommandName = name)

    fun subCommandCall(call: SubCommandCall): Builder =
        copy(subCommandCallList = subCommandCallList + call)

    fun subCommandFinalize(): Builder = if (subCommandName == null) {
        this
    } else {
        copy(
            subCommandName = null,
            subCommandCallList = emptyList(),
            subCommandList = subCommandList + SubCommand(subCommandName, subCommandCallList)
        )
    }

    fun exampleStateBeforeItem(item: Item): Builder =
        copy(exampleStateBeforeItemList = exampleStateBeforeItemList + item)

    fun exampleLog(message: String): Builder =
        copy(exampleLogMessageList = exampleLogMessageList + message)

    fun exampleStateAfterItem(item: Item): Builder =
        copy(exampleStateAfterItemList = exampleStateAfterItemList + item)

    fun exampleFinalize(): Builder =
        copy(
            exampleParameterList = emptyList(),
            exampleStateBeforeItemList = emptyList(),
            exampleLogMessageList = emptyList(),
            exampleStateAfterItemList = emptyList(),
            exampleList = exampleList + Example(
                exampleParameterList,
                Items.fromItemList(exampleStateBeforeItemList),
                exampleLogMessageList,
                Items.fromItemList(exampleStateAfterItemList)
            )
        )

    fun exampleParameter(parameter: Pair<String, Any>): Builder =
        copy(exampleParameterList = exampleParameterList + parameter)

    fun build(): Script =
        finalize().buildWithoutFinalize()

    private fun finalize(): Builder = subCommandFinalize().exampleFinalize()

    private fun buildWithoutFinalize(): Script =
        Script(inputNameList, topCommandList, subCommandList, exampleList)
}
