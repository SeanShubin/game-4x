package com.seanshubin.game_4x.prototype4

object Format {
    fun formatItem(item: Item): String = item.attributes.map { (name, value) ->
        if (name == "name") {
            "$value"
        } else {
            "$name=$value"
        }
    }.joinToString(" ", "{", "}")
}
