package io.directional.wine.API

import io.directional.wine.Dto.CMRespDto
import io.directional.wine.Dto.FilterDto
import io.directional.wine.Entity.Wine
import io.directional.wine.service.WineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/wines")
class WineApi (
        @Autowired
        private var wineService: WineService
){

    @PostMapping("/new")
    fun create(@RequestBody wine: Wine):Any?{
        wineService.create(wine)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.CREATED.value(),"ok",wine))
    }
    @DeleteMapping("/{wineId}")
    fun delete(@PathVariable wineId: Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"delete",wineService.delete(wineId)))
    }
    @PutMapping("/{wineId}")
    fun updateAll(@RequestBody wine: Wine, @PathVariable wineId:Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"update",wineService.update(wine, wineId)))
    }


    @GetMapping("/names")
    fun searchWine(@RequestParam wineNames: String): ResponseEntity<Any>{
        var orgWines = wineService.searchWineByWineName(wineNames)
        var wines = wineService.matchingDtoTypeForWine(orgWines)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wines))
    }

    //sort parameter를 기준으로 오름차순 정렬함
    @GetMapping("/sort")
    fun sortWine(sort: String): ResponseEntity<Any>{
        var wineIds = wineService.sortWine(sort)
        var wines = wineService.matchingDtoTypeForInt(wineIds)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wines))
    }


    @GetMapping("/filter")
    fun filterWine(@RequestBody filter: FilterDto): ResponseEntity<Any>{
        var orgWines = wineService.filterWine(filter)
        var wines = wineService.matchingDtoTypeForWine(orgWines)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wines))
    }

}
