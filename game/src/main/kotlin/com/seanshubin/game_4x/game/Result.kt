package com.seanshubin.game_4x.game

sealed interface Result{
    fun assertSuccess():Universe =
        when(this) {
            is Success -> universe
            is Failure -> throw RuntimeException("failure: $message")
        }
    companion object {
        fun success(universe:Universe, message:String):Success = Success(universe, message)
        fun failure(message:String):Failure = Failure(message)
        data class Success(val universe:Universe, val message:String):Result
        data class Failure(val message:String):Result
    }
}
