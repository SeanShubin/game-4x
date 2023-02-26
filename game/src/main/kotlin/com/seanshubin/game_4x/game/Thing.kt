package com.seanshubin.game_4x.game

data class Thing(val attributes:List<Pair<String, Attribute>>){
    constructor(vararg pairs:Pair<String, Any>):this(pairsToAttributePairs(*pairs))
    fun toLines():List<String> = attributes.flatMap{it.toLines()}
    fun partiallyMatches(that:Thing):Boolean {
        val thisAttributeNames = this.attributeNames().toSet()
        val thatAttributeNames = that.attributeNames().toSet()
        val commonAttributeNames = thisAttributeNames.intersect(thatAttributeNames).toList()
        val commonThis = this.onlyAttributes(commonAttributeNames)
        val commonThat = that.onlyAttributes(commonAttributeNames)
        return commonThis == commonThat
    }
    fun attributeNames():List<String> = attributes.map { it.first }.distinct()
    fun onlyAttributes(names:List<String>):Thing = Thing(attributes = attributes.filter {
        names.contains(it.first)
    })
    companion object {
        sealed interface Attribute
        data class StringAttribute(val stringValue:String):Attribute{
            override fun toString(): String = """"$stringValue""""
        }
        data class IntAttribute(val intValue:Int):Attribute {
            override fun toString(): String = intValue.toString()
        }
        data class BooleanAttribute(val booleanValue:Boolean):Attribute{
            override fun toString(): String = booleanValue.toString()
        }
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
        private fun Pair<String, Attribute>.toLines():List<String>{
            val (name, value) = this
            return listOf("$name = $value")
        }
    }
}
