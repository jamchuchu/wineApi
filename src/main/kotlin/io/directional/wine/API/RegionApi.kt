package io.directional.wine.API

import io.directional.wine.Dto.CMRespDto
import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Region
import io.directional.wine.Entity.Winery
import io.directional.wine.service.RegionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/regions")
class RegionApi (
    @Autowired
    private var regionService: RegionService
){

    @PostMapping("/new")
    fun create(@RequestBody region: Region):Any?{
        regionService.create(region)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.CREATED.value(),"ok",region))
    }
    @DeleteMapping("/{regionId}")
    fun delete(@PathVariable regionId: Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"delete",regionService.delete(regionId)))
    }
    @PutMapping("/{regionId}")
    fun updateAll(@RequestBody region: Region, @PathVariable regionId:Int):Any?{
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"update",regionService.update(region, regionId)))
    }
    //search
    @GetMapping("/names")
    fun searchRegion(@RequestParam regionName: String): ResponseEntity<Any> {
        var regionOrg = regionService.searchRegionByRegionName(regionName)
        var region = regionService.matchingDtoTypeForRegion(regionOrg)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", region))
    }
    @GetMapping("/sort/{criteria}")
    fun sortRegion(@PathVariable criteria : String):Any?{
        var regionOrg = regionService.sortRegion(criteria)
        var region = regionService.matchingDtoTypeForInt(regionOrg)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", region))
    }

    @GetMapping("/parentRegion")
    fun filterByparentRegion(@RequestParam parent : String):Any?{
        var regionOrg = regionService.searchRegionByParentRegionName(parent)
        var region = regionService.matchingDtoTypeForInt(regionOrg)
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", region))
    }
}