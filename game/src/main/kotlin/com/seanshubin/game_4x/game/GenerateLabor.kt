package com.seanshubin.game_4x.game

object GenerateLabor: AllLandsCommand(){
    override fun executeOnLand(land: Land): Land? = land.generateLabor()
}
