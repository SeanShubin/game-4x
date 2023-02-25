package com.seanshubin.game_4x.game

data class Thing(val attributes:List<Pair<String, Attribute>>){
    constructor(vararg pairs:Pair<String, Any>):this(pairsToAttributePairs(*pairs))
    companion object {
        sealed interface Attribute
        data class StringAttribute(val stringValue:String):Attribute
        data class IntAttribute(val intValue:Int):Attribute
        data class BooleanAttribute(val booleanValue:Boolean):Attribute
        fun pairsToAttributePairs(vararg pairs:Pair<String, Any>):List<Pair<String, Attribute>> =
            pairs.map{(first, second) ->
                first to second.toAttribute()
            }

        private fun Any.toAttribute():Attribute =
            when(this) {
                is String -> StringAttribute(this)
                is Int -> IntAttribute(this)
                is Boolean -> BooleanAttribute(this)
                else -> throw RuntimeException("Unsupported type '${this.javaClass.simpleName}'")
            }
    }
}
