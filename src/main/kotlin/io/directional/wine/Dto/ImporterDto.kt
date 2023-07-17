package io.directional.wine.Dto

import lombok.AllArgsConstructor

@AllArgsConstructor
data class ImporterDto (
    var importer : String,
    var wineNames: List<String>
        )