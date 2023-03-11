package com.seanshubin.game_4x.prototype4

object Messages {
    fun unableToRemove(count:Int, item:Item):String {
        val itemString = Format.formatItem(item)
        return "Unable to remove item $itemString, count is $count"
    }
    fun changedQuantity(oldCount:Int, newCount:Int, item:Item):String {
        val itemString = Format.formatItem(item)
        return "$oldCount -> $newCount $itemString"
    }
}
