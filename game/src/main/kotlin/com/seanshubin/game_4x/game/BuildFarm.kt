package com.seanshubin.game_4x.game

object BuildFarm: AllLandsCommand(){
    override fun executeOnLand(land: Land): Land? = land.buildGatherer(Names.FOOD)
}
