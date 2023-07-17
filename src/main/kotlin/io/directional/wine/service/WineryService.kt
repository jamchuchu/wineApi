package io.directional.wine.service

import io.directional.wine.Dto.WineryDtoForSingle
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Wine
import io.directional.wine.Entity.Winery
import io.directional.wine.repository.WineRepository
import io.directional.wine.repository.WineryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WineryService (
    @Autowired
    private var wineryRepository:WineryRepository,

    @Autowired
    private var wineService: WineService
        ){

    //create
    @Transactional
    fun create(winery: Winery):Int{
        return wineryRepository.create(winery)
    }
    @Transactional
    fun delete(wineryId: Int):Int{
        return wineryRepository.delete(wineryId)
    }
    @Transactional
    fun update(winery: Winery, wineryId: Int):Int{
        return wineryRepository.update(winery, wineryId)
    }

    //search
    @Transactional
    fun searchWineryByWineryId(wineryId: Int):Winery{
        return wineryRepository.searchWineryByWineryId(wineryId)
    }

    @Transactional
    fun searchWinerysByWineryIds(wineryIds : List<Int>):List<Winery> {
        val wineries: MutableList<Winery> = mutableListOf()
        wineryIds.forEach { id ->
            wineries.add(searchWineryByWineryId(id))
        }
        return wineries
    }

    @Transactional
    fun searchWineryByWineryName(wineryName : String): List<Winery>{
        return wineryRepository.searchWineryByWineryName(wineryName)
    }

    //sort
    fun sortWinery(language: String):List<Winery> {
        var wineryIds =  wineryRepository.sortWinery(language)
        return searchWinerysByWineryIds(wineryIds)
    }

    //filter
    fun searchWineryByRegion(region:String):List<Winery>{
        var wineryIds =  wineryRepository.searchWineryByRegion(region)
        return searchWinerysByWineryIds(wineryIds)
    }


        //wineries.size = 1-> singleWinery
    fun changeWineriesToWineryDtoForSingle(wineries: List<Winery>): WineryDtoForSingle {
        var winery = wineries.get(0)
        var wineIdsBywinery = wineService.searchWinesByWinery(winery.nameEng)
        var wines = wineService.searchWinesByWineIds(wineIdsBywinery)
        var wineryforSingle = WineryDtoForSingle(winery, wines)
        return wineryforSingle
    }



}