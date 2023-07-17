package io.directional.wine.service

import io.directional.wine.Dto.ImporterDto
import io.directional.wine.Entity.Region
import io.directional.wine.repository.ImporterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImporterService (
    @Autowired
    private var importerRepository: ImporterRepository
        ){

    fun searchImportersByImporterName(importerName: String): ImporterDto{
        var wineNames = importerRepository.searchImportersByImporterName(importerName)
        return ImporterDto(importerName, wineNames)
    }

    fun sortImporter():List<String>{
        return importerRepository.sortImporter()
    }



}