package io.directional.wine.repository

import io.directional.wine.Entity.Wine
import io.directional.wine.Entity.Winery
import org.apache.ibatis.annotations.Mapper

@Mapper
interface WineryRepository {
    fun create(winery: Winery):Int
    fun delete(wineryId: Int):Int
    fun update(winery: Winery, wineryId: Int):Int


    fun searchWineryByWineryName(wineryName : String): List<Winery>
    fun searchWineryByWineryId(wineryId: Int):Winery
    fun sortWinery(language: String):List<Int>
    fun searchWineryByRegion(region:String):List<Int>


    }