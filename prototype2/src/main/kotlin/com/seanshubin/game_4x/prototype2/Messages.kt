package com.seanshubin.game_4x.prototype2

object Messages {
    fun quantityChanged(oldQuantity: Int, newQuantity: Int, item: Item): List<String> = listOf(
        "$oldQuantity -> $newQuantity changed quantity of ${Format.formatItem(item)}"
    )

    fun missingParameter(index: Int): String = "Missing parameter ${index + 1}"
    fun invalidSyntax(line: String): String = "Invalid syntax for '$line'"
    fun commandNotFound(name: String): String = "Command '$name' not found"
}
