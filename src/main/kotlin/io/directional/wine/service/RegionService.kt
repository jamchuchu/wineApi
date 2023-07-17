package io.directional.wine.service

import io.directional.wine.Dto.RegionDtoForSingle
import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Winery
import io.directional.wine.repository.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegionService (
    @Autowired
    private var regionRepository: RegionRepository
    ){
    @Transactional
    fun create(region: Region):Int{
        return regionRepository.create(region)
    }
    @Transactional
    fun delete(regionId: Int):Int{
        return regionRepository.delete(regionId)
    }
    @Transactional
    fun update(region: Region, regionId: Int):Int{
        return regionRepository.update(region, regionId)
    }

    fun searchRegionByRegionId(regionId: Int): Region{
        return regionRepository.searchRegionByRegionId(regionId)
    }
    fun searchRegionsByRegionIds(regionIds: List<Int>): List<Region>{
        val regions: MutableList<Region> = mutableListOf()
        regionIds.forEach{id ->
            regions.add(searchRegionByRegionId(id))
        }
        return regions
    }

    fun searchRegionByRegionName(regionName: String):Region{
        return regionRepository.searchRegionByRegionName(regionName)
    }
    fun sortRegion(critera: String):List<Int>{
        return regionRepository.sortRegion(critera)
    }
    fun searchRegionByParentRegionName(parentRegionName: String):List<Int>{
        return regionRepository.searchRegionByParentRegionName(parentRegionName)
    }


    fun searchAllParentNamesByRegionId(regionId: Int): List<String>{
        val parentNames: MutableList<String> = mutableListOf()
        var firstName = searchRegionNameByRegionId(regionId)
        var regionName = searchParentRegionNameByRegionName(firstName)
        while(regionName.isNotBlank()){
            parentNames.add(regionName)
            regionName = searchParentRegionNameByRegionName(regionName)
        }
        return parentNames
    }

    fun searchAllParentNamesByRegionIdWithRecentRegion(regionId: Int): List<String>{
        val parentNames: MutableList<String> = mutableListOf()
        var firstName = searchRegionNameByRegionId(regionId)
        parentNames.add(firstName)
        var regionName = searchParentRegionNameByRegionName(firstName)
        while(regionName.isNotBlank()){
            parentNames.add(regionName)
            regionName = searchParentRegionNameByRegionName(regionName)
        }
        return parentNames
    }
    fun searchTopParentNamesByRegionId(regionId: Int): String{
        var preName = searchRegionNameByRegionId(regionId)
        var parentName = searchParentRegionNameByRegionName(preName)
        while(parentName.isNotBlank()){
            preName = parentName
            parentName = searchParentRegionNameByRegionName(preName)
        }
        return preName
    }

    fun searchParentRegionNameByRegionName(regionName: String):String{
        return regionRepository.searchParentRegionNameByRegionName(regionName)
    }

    fun matchingDtoTypeForRegion(orgRegions: Any):Any{
        val regions : MutableList<Any?> = mutableListOf()
        if (orgRegions is Region){
            regions.add(changeRegionForSingleTypeForRegion(orgRegions))
        }
        else if (orgRegions is List<*>){
            if (orgRegions.size == 1){
                regions.add(changeRegionForSingleTypeForRegion(orgRegions.toList().get(0) as Region))
            }else{
                orgRegions.forEach{region ->
                    if(region is Region) {
                        regions.add(region.regionId)
                    }
                }
            }
        }
        return regions
    }


    fun matchingDtoTypeForInt(regionIds: Any):Any{
        val regions : MutableList<Any?> = mutableListOf()
        if (regionIds is Int){
            regions.add(changeRegionForSingleTypeForInt(regionIds))
        }
        else if (regionIds is List<*>){
            if (regions.size == 1){
                regions.add(changeRegionForSingleTypeForInt(regionIds.toList().get(0) as Int))
            }else{
                regions.addAll(searchRegionNamesByRegionIds(regionIds.toList() as List<Int>))
            }
        }
        return regions
    }


    //복수타입 = 이름만
    fun searchRegionNameByRegionId(regionId: Int): String{
        return regionRepository.searchRegionNameByRegionId(regionId)
    }
    //복수타입 여러개를 한번에
    fun searchRegionNamesByRegionIds(regionIds: List<Int>): List<String>{
        val regions: MutableList<String> = mutableListOf()
        regionIds.forEach{id ->
            regions.add(searchRegionNameByRegionId(id))
        }
        return regions
    }
    //single type 변경
    fun changeRegionForSingleTypeForInt(regionId: Int):RegionDtoForSingle{
        var parentNames = searchAllParentNamesByRegionId(regionId)
        var regionName = searchRegionNameByRegionId(regionId)
        var grapeNames =regionRepository.findInfoForSingleTypeAsGrape(regionName)
        var wineryNames = regionRepository.findInfoForSingleTypeAsWinery(regionName)
        var wineNames = regionRepository.findInfoForSingleTypeAsWine(regionName)
        var regions = RegionDtoForSingle(searchRegionByRegionId(regionId),parentNames,grapeNames,wineryNames,wineNames )
        return regions
    }
    fun changeRegionForSingleTypeForRegion(region: Region):RegionDtoForSingle{
        var parentNames = searchAllParentNamesByRegionId(region.regionId)
        var grapeNames =regionRepository.findInfoForSingleTypeAsGrape(region.regionNameEng)
        var wineryNames = regionRepository.findInfoForSingleTypeAsWinery(region.regionNameEng)
        var wineNames = regionRepository.findInfoForSingleTypeAsWine(region.regionNameEng)
        var regions = RegionDtoForSingle(region,parentNames,grapeNames,wineryNames,wineNames  )
        return regions
    }

    fun findInfoForSingleTypeAsGrape(regionName: String) :List<String>{
        return regionRepository.findInfoForSingleTypeAsWine(regionName)
    }



}