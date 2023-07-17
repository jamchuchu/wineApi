package io.directional.wine.repository

import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Wine
import org.apache.ibatis.annotations.Mapper

@Mapper
interface GrapeRepository {
    fun create(grape: Grape):Int
    fun delete(grapeId: Int):Int
    fun update(grape: Grape, grapeId: Int):Int


    fun searchGrapeByGrapeName(grapeName: String):Grape
    fun sortGrape(criteria: String):List<Int>
    fun searchGrapeNameByRegion(region: String):List<String>
    fun searchWinesByGrape(grapeName: String):List<String>
    fun searchGrapeByGrapeId(grapeId: Int): Grape
}