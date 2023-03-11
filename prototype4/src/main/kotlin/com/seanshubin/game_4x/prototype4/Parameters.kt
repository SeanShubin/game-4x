package com.seanshubin.game_4x.prototype4

data class Parameters(
    val unvalidated: List<Any>,
    val messages: List<String> = emptyList()
) {
    fun requireAtLeastCount(requiredCount: Int): Parameters {
        val actualCount = unvalidated.size
        return if (actualCount < requiredCount) {
            val message = "wrong number of parameters, expected at least $requiredCount, got $actualCount"
            addMessage(message)
        } else {
            this
        }
    }

    fun requireIsItem(index: Int): Parameters {
        val atIndex = unvalidated.getOrNull(index) ?: return this
        return when (atIndex) {
            is Item -> this
            else -> wrongTypeAt(index, Item::javaClass.name, atIndex.javaClass.simpleName)
        }
    }

    fun requireIsString(index: Int): Parameters {
        val atIndex = unvalidated.getOrNull(index) ?: return this
        return when (atIndex) {
            is String -> this
            else -> wrongTypeAt(index, String::javaClass.name, atIndex.javaClass.simpleName)
        }
    }

    fun requireIsInt(index: Int): Parameters {
        val atIndex = unvalidated.getOrNull(index) ?: return this
        return when (atIndex) {
            is Int -> this
            else -> wrongTypeAt(index, Int.Companion::class.java.simpleName, atIndex.javaClass.simpleName)
        }
    }

    fun itemAt(index: Int): Item = unvalidated[index] as Item
    fun stringAt(index: Int): String = unvalidated[index] as String
    fun intOrDefaultAt(index: Int, default: Int): Int = (unvalidated.getOrNull(index) ?: default) as Int
    fun valid(): Boolean = messages.isEmpty()
    fun notValid(): Boolean = !valid()
    private fun addMessage(message: String): Parameters = copy(messages = messages + message)
    private fun missingParameterAt(index: Int): Parameters = addMessage(
        "Missing parameter at index $index"
    )

    private fun wrongTypeAt(index: Int, expectedType: String, actualType: String): Parameters = addMessage(
        "Expected type $expectedType at index $index, got $actualType"
    )
}
