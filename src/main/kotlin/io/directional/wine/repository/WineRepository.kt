package io.directional.wine.repository

import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Wine
import org.apache.ibatis.annotations.Mapper

@Mapper
interface WineRepository{
    fun create(wine: Wine):Int
    fun delete(wineId: Int):Int
    fun update(wine: Wine, wineId: Int):Int


    // search
    fun searchWineByWineName(wineName: String): List<Wine>
    fun searchWinesByWinery(wineryName: String): List<Int>
    fun searchWineByWineId(wineId : Int): Wine

    //sort
    fun sortWineBycriterion(criterion: String):List<Int>

    //filter range x
    fun filterWineWithoutRange(filterName: String, property: String):List<Wine>
    //filter range o
    fun filterWineWithRange(filterName: String, min:Int, max:Int):List<Wine>


    fun searchAromaByWineName(wineName: String):String
    fun searchPairingByWineName(wineName: String):String
    fun searchGrapeByWineName(wineName: String):List<String>


}

