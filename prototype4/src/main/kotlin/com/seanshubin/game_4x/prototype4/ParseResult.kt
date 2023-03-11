package com.seanshubin.game_4x.prototype4

sealed interface ParseResult{
    data class Success(val call:Call):ParseResult
    class Failure(val messages:List<String>):ParseResult
}
