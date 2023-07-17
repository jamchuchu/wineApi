package io.directional.wine.API

import io.directional.wine.Dto.CMRespDto
import io.directional.wine.Entity.Wine
import io.directional.wine.Entity.Winery
import io.directional.wine.service.WineryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/wineries")
class WineryApi (
    @Autowired
    private var wineryService: WineryService
    ){

    @PostMapping("/new")
    fun create(@RequestBody winery: Winery):Any?{
        wineryService.create(winery)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.CREATED.value(),"ok",winery))
    }
    @DeleteMapping("/{wineryId}")
    fun delete(@PathVariable wineryId: Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"delete",wineryService.delete(wineryId)))
    }
    @PutMapping("/{wineryId}")
    fun updateAll(@RequestBody winery: Winery, @PathVariable wineryId:Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"update",wineryService.update(winery, wineryId)))
    }
    //search
    @GetMapping("/name/{wineryName}")
    fun searchWinery(@PathVariable wineryName: String): ResponseEntity<Any> {
        val wineries: Any
        var wineryList = wineryService.searchWineryByWineryName(wineryName)
        if(wineryList.size == 1){
            wineries = wineryService.changeWineriesToWineryDtoForSingle(wineryList)
        }else{
            wineries = wineryList
        }
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wineries))
    }
    //sort(kr, eng)
    @GetMapping("/sort/{language}")
    fun sortWinery(@PathVariable language: String):ResponseEntity<Any>{
        var wineries = wineryService.sortWinery(language)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wineries))
    }

    //filter
    @GetMapping("/region")
    fun serachWineryByRegion(@RequestParam region:String):ResponseEntity<Any>{
        val wineries: Any
        var wineryList = wineryService.searchWineryByRegion(region)
        if(wineryList.size == 1){
            wineries = wineryService.changeWineriesToWineryDtoForSingle(wineryList)
        }else{
            wineries = wineryList
        }
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", wineries))
    }


}