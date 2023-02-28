package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.HasToObject
import com.seanshubin.game_4x.game.Universe

interface UniverseCommand:HasToObject {
    fun execute(universe: Universe): Either<UniverseFailure, UniverseSuccess>
}
