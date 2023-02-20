package com.seanshubin.game_4x.game

object GenerateFood: AllLandsCommand(){
    override fun executeOnLand(land: Land): Land? = land.generate(Names.FOOD)
}
