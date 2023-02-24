package com.seanshubin.game_4x.game

sealed interface Result{
    fun assertSuccess():Universe =
        when(this) {
            is Success -> universe
            is Failure -> throw RuntimeException("failure: $message")
        }
    companion object {
        fun success(message:String, universe:Universe):Success = Success(message, universe)
        fun failure(message:String):Failure = Failure(message)
        data class Success(val message:String, val universe:Universe):Result
        data class Failure(val message:String):Result
    }
}
