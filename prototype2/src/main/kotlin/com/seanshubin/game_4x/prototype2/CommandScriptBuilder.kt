package com.seanshubin.game_4x.prototype2

data class CommandScriptBuilder(
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
)  {
    fun input(name: String): CommandScriptBuilder =
        copy(inputNameList = inputNameList + name)

    fun topCommand(topCommand: TopCommand): CommandScriptBuilder =
        copy(topCommandList = topCommandList + topCommand)

    fun subCommandName(name: String): CommandScriptBuilder =
        copy(subCommandName = name)

    fun subCommandCall(call: SubCommandCall): CommandScriptBuilder =
        copy(subCommandCallList = subCommandCallList + call)

    fun subCommandFinalize(): CommandScriptBuilder = if(subCommandName == null) {
        this
    } else {
        copy(
            subCommandName = null,
            subCommandCallList = emptyList(),
            subCommandList = subCommandList + SubCommand(subCommandName, subCommandCallList)
        )
    }

    fun exampleStateBeforeItem(item: Item): CommandScriptBuilder =
        copy(exampleStateBeforeItemList = exampleStateBeforeItemList + item)

    fun exampleLog(message: String): CommandScriptBuilder =
        copy(exampleLogMessageList = exampleLogMessageList + message)

    fun exampleStateAfterItem(item: Item): CommandScriptBuilder =
        copy(exampleStateAfterItemList = exampleStateAfterItemList + item)

    fun exampleFinalize(): CommandScriptBuilder =
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

    fun exampleParameter(parameter: Pair<String, Any>): CommandScriptBuilder =
        copy(exampleParameterList = exampleParameterList + parameter)

    fun build(): CommandScript =
        finalize().buildWithoutFinalize()

    private fun finalize():CommandScriptBuilder = subCommandFinalize().exampleFinalize()

    private fun buildWithoutFinalize():CommandScript =
        CommandScript(inputNameList, topCommandList, subCommandList, exampleList)
}
