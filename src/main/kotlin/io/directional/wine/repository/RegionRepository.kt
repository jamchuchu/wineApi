package io.directional.wine.repository

import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Winery
import org.apache.ibatis.annotations.Mapper

@Mapper
interface RegionRepository {
    fun create(region: Region):Int
    fun delete(regionId: Int):Int
    fun update(region: Region, regionId: Int):Int


    fun searchRegionByRegionId(regionId: Int): Region
    fun searchRegionNameByRegionId(regionId: Int): String

    fun searchRegionByRegionName(regionName: String):Region
    fun sortRegion(critera :String):List<Int>
    fun searchRegionByParentRegionName(parentRegionName: String):List<Int>
    fun searchParentRegionNameByRegionName(regionName: String):String

    fun findInfoForSingleTypeAsGrape(regionName: String):List<String>
    fun findInfoForSingleTypeAsWinery(regionName: String):List<String>
    fun findInfoForSingleTypeAsWine(regionName: String):List<String>

}