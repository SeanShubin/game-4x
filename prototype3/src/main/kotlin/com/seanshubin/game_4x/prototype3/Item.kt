package com.seanshubin.game_4x.prototype3

import com.seanshubin.game_4x.prototype3.PrimitiveUtil.valuesToPrimitive

data class Item(val attributes:Map<String, PrimitiveValue> = emptyMap()):Value {
    companion object {
        fun Map<String, String>.toItem():Item = Item(this.valuesToPrimitive())
    }
}
