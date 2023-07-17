package io.directional.wine.repository

import io.directional.wine.Dto.ImporterDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ImporterRepository {
    fun searchImportersByImporterName(importerName: String): List<String>
    fun sortImporter():List<String>
}