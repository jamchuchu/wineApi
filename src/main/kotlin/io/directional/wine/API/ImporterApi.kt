package io.directional.wine.API

import io.directional.wine.Dto.CMRespDto
import io.directional.wine.service.ImporterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/importers")
class ImporterApi(
    @Autowired
    private var importerService: ImporterService
) {
    @GetMapping("/names")
    fun searchImportersByImporterName(@RequestParam importerName : String):Any?{
        var importers = importerService.searchImportersByImporterName(importerName)
         return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", importers))
    }

    @GetMapping("/sort")
    fun searchImportersByImporterName():Any?{
        var importers = importerService.sortImporter()
        return ResponseEntity.ok().body(CMRespDto(HttpStatus.OK.value(),"ok", importers))
    }

}