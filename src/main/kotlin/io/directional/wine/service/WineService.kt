package io.directional.wine.service

import io.directional.wine.Dto.FilterDto
import io.directional.wine.Dto.WineDtoForMulti
import io.directional.wine.Dto.WineDtoForSingle
import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Wine
import io.directional.wine.repository.GrapeRepository
import io.directional.wine.repository.RegionRepository
import io.directional.wine.repository.WineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WineService(
    @Autowired
    private var wineRepository: WineRepository,
    @Autowired
    private var regionservice: RegionService
    ){

    @Transactional
    fun create(wine: Wine):Int{
        return wineRepository.create(wine)
    }
    @Transactional
    fun delete(wineId: Int):Int{
        return wineRepository.delete(wineId)
    }
    @Transactional
    fun update(wine: Wine, wineId: Int):Int{
        return wineRepository.update(wine, wineId)
    }

    //searech
    @Transactional
    fun searchWineByWineId(wineId : Int):Wine{
        return wineRepository.searchWineByWineId(wineId)
    }
    @Transactional
    fun searchWinesByWineIds(wineIds : List<Int>):List<Wine>{
        val wines: MutableList<Wine> = mutableListOf()
        wineIds.forEach{id ->
            wines.add(searchWineByWineId(id))
        }
        return wines
    }


    //wine 이름으로 단일 검색
    @Transactional
    fun searchWineByWineName(wineName: String): List<Wine> {
        return wineRepository.searchWineByWineName(wineName)
    }



    //winery의 wine 찾기
    fun searchWinesByWinery(wineryName: String):List<Int>{
        return wineRepository.searchWinesByWinery(wineryName)
    }

    //sort
    fun sortWine(sort: String):List<Int> {
        return wineRepository.sortWineBycriterion(sort)
    }



    //filter
    fun filterWine(filter: FilterDto):List<Wine>{
        return when (filter.property) {
            is String -> wineRepository.filterWineWithoutRange(filter.filterName, filter.property as String)
            is List<*> -> {
                val properties = filter.property as List<*>
                if (properties.size == 2 && properties[0] is Int && properties[1] is Int) {
                    val minValue = properties[0] as Int
                    val maxValue = properties[1] as Int
                    wineRepository.filterWineWithRange(filter.filterName, minValue, maxValue)
                } else {
                    throw IllegalArgumentException("Invalid property format for range filtering")
                }
            }
            else -> throw IllegalArgumentException("Invalid property type")
        }
    }



    fun matchingDtoTypeForWine(orgWines: Any):Any{
        val wines : MutableList<Any?> = mutableListOf()
        if (orgWines is Wine){
            wines.add(changeWineSingleTypeWinesForWine(orgWines))
        }
        else if (orgWines is List<*>){
            if (orgWines.size == 1){
                wines.add(changeWineSingleTypeWinesForWine(orgWines.toList().get(0) as Wine))
            }else{
                wines.addAll(changeWinesMultipleTypeWinesForWine(orgWines as List<Wine>))
            }
        }
        return wines
    }


    fun matchingDtoTypeForInt(wineIds: Any):Any{
        val wines : MutableList<Any?> = mutableListOf()
        if (wineIds is Int){
            wines.add(changeWineSingleTypeWinesForInt(wineIds.toInt()))
        }
        else if (wineIds is List<*>){
            if (wineIds.size == 1){
                wines.add(changeWineSingleTypeWinesForInt(wineIds.toList().get(0) as Int))
            }else{
                wines.addAll(changeWinesMultipleTypeWinesForInt(wineIds.toList() as List<Int>))
            }
        }
        return wines
    }

    fun changeWineSingleTypeWinesForWine(wine: Wine):WineDtoForSingle{
        var regionId = regionservice.searchRegionByRegionName(wine.regionNameEng).regionId
        var allRegions = regionservice.searchAllParentNamesByRegionIdWithRecentRegion(regionId)
        var aroma = wineRepository.searchAromaByWineName(wine.nameEng)
        var pairing =  wineRepository.searchPairingByWineName(wine.nameEng)
        var grape =  wineRepository.searchGrapeByWineName(wine.nameEng)
        return WineDtoForSingle(wine, allRegions, aroma, pairing, grape)
    }


    fun changeWineSingleTypeWinesForInt(wineId: Int):WineDtoForSingle{
        var wine = searchWineByWineId(wineId)
        return changeWineSingleTypeWinesForWine(wine)
    }



    fun changeWineMultipleTypeWineForInt(wineId:Int):WineDtoForMulti{
        var wine = searchWineByWineId(wineId)
        var regionId = regionservice.searchRegionByRegionName(wine.regionNameEng).regionId
        var topRegionName = regionservice.searchTopParentNamesByRegionId(regionId)
        return WineDtoForMulti(searchWineByWineId(wineId), topRegionName)
    }

    fun changeWinesMultipleTypeWinesForInt(wineIds: List<Int>):List<WineDtoForMulti>{
        val wines: MutableList<WineDtoForMulti> = mutableListOf()
        wineIds.forEach {id ->
            wines.add(changeWineMultipleTypeWineForInt(id))
        }
        return wines
    }

    fun changeWineMultipleTypeWineForWine(wine:Wine):WineDtoForMulti{
        var regionId = regionservice.searchRegionByRegionName(wine.regionNameEng).regionId
        var topRegionName = regionservice.searchTopParentNamesByRegionId(regionId)
        return WineDtoForMulti(searchWineByWineId(wine.wineId), topRegionName)
    }

    fun changeWinesMultipleTypeWinesForWine(orgWines: List<Wine>):List<WineDtoForMulti>{
        val wines: MutableList<WineDtoForMulti> = mutableListOf()
        orgWines.forEach {wine ->
             wines.add(changeWineMultipleTypeWineForWine(wine))
        }
        return wines
    }




}