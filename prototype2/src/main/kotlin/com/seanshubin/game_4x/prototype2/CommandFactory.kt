package com.seanshubin.game_4x.prototype2

import arrow.core.Either

interface CommandFactory {
    fun build(name: String, parameters: List<Item>): Either<String, Command>
}
