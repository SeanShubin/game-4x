package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlin.test.Test

class EitherTest {
    @Test
    fun tryme() {
        val list: List<Either<String, Int>> = listOf(
            "a".left(),
            1.right(),
            "b".left(),
            2.right(),
            "c".left(),
            3.right()
        )
        list.map { it.getOrNull() }.mapNotNull { it }.forEach(::println)
    }

}