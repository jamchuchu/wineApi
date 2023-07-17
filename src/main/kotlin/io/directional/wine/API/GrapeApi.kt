package io.directional.wine.API

import io.directional.wine.Dto.CMRespDto
import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.service.GrapeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.print.DocFlavor.STRING

@RestController
@RequestMapping("/api/grapes")
class GrapeApi (
    @Autowired
    private var grapeService: GrapeService
        ){

    @PostMapping("/new")
    fun create(@RequestBody grape: Grape):Any?{
        grapeService.create(grape)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.CREATED.value(),"ok",grape))
    }
    @DeleteMapping("/{grapeId}")
    fun delete(@PathVariable grapeId: Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"delete",grapeService.delete(grapeId)))
    }

    @PutMapping("/{grapeId}")
    fun updateAll(@RequestBody grape: Grape, @PathVariable grapeId:Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"update",grapeService.update(grape, grapeId)))
    }

    //search
    @GetMapping("/names")
    fun searchGrape(@RequestParam grapeName: String): ResponseEntity<Any> {
        var grapeOrg = grapeService.searchGrapeByGrapeName(grapeName)
        var grape = grapeService.matchingDtoTypeForGrape(grapeOrg)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", grape))
    }

    //기준: 이름, 산도, 바디감, 단맛, 타닌 중 1
    @GetMapping("/sort/{sort}")
    fun sortGrape(@PathVariable sort: String): ResponseEntity<Any>{
        var grapeOrg = grapeService.sortGrape(sort)
        var grape = grapeService.matchingDtoTypeForInt(grapeOrg)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", grape))
    }

    @GetMapping("/region")
    fun filterWine(@RequestParam region: String): ResponseEntity<Any>{
        var grapeNames = grapeService.searchGrapeNameByRegion(region)
        var grapeOrgs:MutableList<Grape> = mutableListOf()
        grapeNames.forEach{name ->
            grapeOrgs.add(grapeService.searchGrapeByGrapeName(name))
        }
        var grapes = grapeService.matchingDtoTypeForGrape(grapeOrgs)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", grapeNames))

    }


}