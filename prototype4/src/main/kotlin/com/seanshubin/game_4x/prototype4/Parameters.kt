package com.seanshubin.game_4x.prototype4

data class Parameters(
    val unvalidated: List<Any>,
    val messages: List<String> = emptyList()
) {
    fun requireCount(expectedCount:Int):Parameters{
        val actualCount = unvalidated.size
        return if(expectedCount != actualCount){
            val message = "wrong number of parameters, expected $expectedCount, got $actualCount"
            addMessage(message)
        } else {
            this
        }
    }
    fun requireIsItem(index:Int):Parameters{
        val atIndex = unvalidated.getOrNull(index) ?: return missingParameterAt(index)
        return when(atIndex){
            is Item -> this
            else -> itemExpectedAt(index, atIndex.javaClass.simpleName)
        }
    }
    fun itemAt(index:Int):Item = unvalidated[index] as Item
    fun valid():Boolean = messages.isEmpty()
    fun notValid():Boolean = !valid()
    private fun addMessage(message:String):Parameters = copy(messages = messages + message)
    private fun missingParameterAt(index:Int):Parameters = addMessage(
        "Missing parameter at index $index"
    )
    private fun itemExpectedAt(index:Int, typeName:String):Parameters = addMessage(
        "Item expected at index $index, got $typeName"
    )
}
