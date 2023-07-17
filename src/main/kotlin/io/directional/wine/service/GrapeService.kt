package io.directional.wine.service

import GrapeDtoForSingle
import io.directional.wine.Dto.GrapeDtoForMulti
import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Wine
import io.directional.wine.repository.GrapeRepository
import io.directional.wine.repository.WineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GrapeService (
    @Autowired
    private var grapeRepository: GrapeRepository,
    @Autowired
    private var wineRepository: WineRepository
        ){

    @Transactional
    fun create(grape: Grape):Int{
        return grapeRepository.create(grape)
    }
    @Transactional
    fun delete(grapeId: Int):Int{
        return grapeRepository.delete(grapeId)
    }
    @Transactional
    fun update(grape: Grape, grapeId: Int):Int{
        return grapeRepository.update(grape,grapeId)
    }
    fun searchGrapeByGrapeName(grapeName: String):Grape{
        return grapeRepository.searchGrapeByGrapeName(grapeName)
    }
    fun searchGrapeNameByRegion(region: String): List<String>{
        return grapeRepository.searchGrapeNameByRegion(region)
    }

    fun sortGrape(criteria: String):List<Int>{
        return grapeRepository.sortGrape(criteria)
    }

    fun searchWinesByGrape(grapeName: String): List<Wine>{
        var wineNames = grapeRepository.searchWinesByGrape(grapeName)
        var wines : MutableList<Wine> = mutableListOf()
        wineNames.forEach{name ->
            wines.addAll(wineRepository.searchWineByWineName(name))
        }
        return wines
    }

    fun searchGrapeByGrapeId(grapeId: Int): Grape{
        return grapeRepository.searchGrapeByGrapeId(grapeId)
    }
    fun searchGrapesByGrapeIds(grapeId: List<Int>): List<Grape>{
        val grapes : MutableList<Grape> = mutableListOf()
        grapeId.forEach {id ->
            grapes.add(searchGrapeByGrapeId(id))
        }
        return grapes
    }


    fun matchingDtoTypeForGrape(grapesOrgs: Any): Any {
        val grapes: MutableList<Any> = mutableListOf()
        if (grapesOrgs is Grape) {
            grapes.add(GrapeDtoForSingle(grapesOrgs, searchWinesByGrape(grapesOrgs.nameEng)))
        } else if (grapesOrgs is List<*>) {
            if (grapesOrgs.size == 1) {
                var grape: Grape = grapesOrgs.get(0) as Grape
                grapes.add(GrapeDtoForSingle(grape, searchWinesByGrape(grape.nameEng)))
            }else {
                grapes.addAll(changeGrapesMultipleTypeForGrape(grapesOrgs as List<Grape>))
            }
        }
        return grapes
    }

    fun matchingDtoTypeForInt(grapeIds: Any):Any{
        val grapes : MutableList<Any?> = mutableListOf()
        if (grapeIds is Int){
            grapes.add(changeGrapeForSigleType(grapeIds.toInt()))
        }
        else if (grapeIds is List<*>){
            if (grapeIds.size == 1){
                grapes.add(changeGrapeForSigleType(grapeIds.toList().get(0) as Int))
            }else{
                grapes.addAll(changeGrapesMultipleType(grapeIds.toList() as List<Int>))
            }
        }
        return grapes
    }

    //single type
    fun changeGrapeForSigleType(grapeId:Int): GrapeDtoForSingle{
        var grape = searchGrapeByGrapeId(grapeId)
        return GrapeDtoForSingle(grape, searchWinesByGrape(grape.nameEng))
    }

    //다수타입형태로만 변환 -1개
    fun changeGrapeForMultiType(grapeId: Int):GrapeDtoForMulti{
        var grape = searchGrapeByGrapeId(grapeId)
        var wines = searchWinesByGrape(grape.nameEng)
        return GrapeDtoForMulti(grape,wines.get(0))
    }
    //여러개 한꺼번에 다수타입으로 변환
    fun changeGrapesMultipleType(grapeIds: List<Int>):List<GrapeDtoForMulti>{
        val grapes : MutableList<GrapeDtoForMulti> = mutableListOf()
        grapeIds.forEach {id->
            grapes.add(changeGrapeForMultiType(id))
        }
        return grapes
    }

    fun changeGrapesMultipleTypeForGrape(grapeOrgs: List<Grape>):List<GrapeDtoForMulti>{
        val grapes : MutableList<GrapeDtoForMulti> = mutableListOf()
        grapeOrgs.forEach {grape->
            var wines = searchWinesByGrape(grape.nameEng)
            grapes.add(GrapeDtoForMulti(grape, wines.get(0)))
        }
        return grapes
    }


}