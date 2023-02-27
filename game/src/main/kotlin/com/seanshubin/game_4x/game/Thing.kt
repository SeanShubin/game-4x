package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Thing(val attributes: List<Pair<String, Attribute>>) : HasToObject {
    constructor(vararg pairs: Pair<String, Any>) : this(pairsToAttributePairs(*pairs))
    val attributeByName:Map<String, Attribute> = attributes.toMap()

    fun toLines(): List<String> = attributes.flatMap { it.toLines() }

    fun setBooleanValue(name:String, value:Boolean):Thing = copy(attributes = attributes.updateWhere({it.first == name}){
        name to BooleanAttribute(value)
    })

    fun isPartOf(that:Thing):Boolean =
        attributes.all { it.isPartOf(that) }

    private fun Pair<String, Attribute>.isPartOf(that:Thing):Boolean {
        val thatAttribute = that.attributeByName[first] ?: return false
        return second == thatAttribute
    }

    override fun toObject(): List<Any> = attributes.map { it.toObject() }

    companion object {
        sealed interface Attribute : HasToObject
        data class StringAttribute(val stringValue: String) : Attribute {
            override fun toObject(): String = stringValue
        }

        data class IntAttribute(val intValue: Int) : Attribute {
            override fun toObject(): Int = intValue
        }

        data class BooleanAttribute(val booleanValue: Boolean) : Attribute {
            override fun toObject(): Boolean = booleanValue
        }

        fun pairsToAttributePairs(vararg pairs: Pair<String, Any>): List<Pair<String, Attribute>> =
            pairs.map { (first, second) ->
                first to second.toAttribute()
            }

        private fun Any.toAttribute(): Attribute =
            when (this) {
                is String -> StringAttribute(this)
                is Int -> IntAttribute(this)
                is Boolean -> BooleanAttribute(this)
                else -> throw RuntimeException("Unsupported type '${this.javaClass.simpleName}'")
            }

        private fun Pair<String, Attribute>.toLines(): List<String> {
            val (name, value) = this
            return listOf("$name = $value")
        }

        private fun Pair<String, Attribute>.toObject(): Map<String, Any> = mapOf(
            "name" to first,
            "value" to second.toObject()
        )
    }
}
