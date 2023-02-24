package com.seanshubin.game_4x.prototype

data class Result(
    val command: Command,
    val game: Game,
    val success: Boolean,
    val details: List<String>
) {
    fun assertSuccess() {
        if (!success) {
            val message = details.joinToString("\n")
            throw RuntimeException(message)
        }
    }

    fun toLines(): List<String> = listOf(
        command.javaClass.simpleName,
        JsonMappers.compact.writeValueAsString(command.toObject()),
        "success = $success",
        "detail lines: ${details.size}"
    ) + details
}