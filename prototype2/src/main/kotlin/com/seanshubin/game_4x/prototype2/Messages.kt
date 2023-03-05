package com.seanshubin.game_4x.prototype2

object Messages {
    fun quantityChanged(oldQuantity:Int, newQuantity:Int, item:Item):List<String> = listOf(
        "$oldQuantity -> $newQuantity changed quantity of ${Format.formatItem(item)}"
    )
}
