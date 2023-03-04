package com.seanshubin.game_4x.prototype2

data class Line(val source:String, val index:Int, val text:String){
    override fun toString(): String = "$source[${index+1}]: $text"
}
