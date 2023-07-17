package io.directional.wine.Dto

import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Wine

data class GrapeDtoForMulti (
    var grapeId: Int,
    var nameKr: String,
    var nameEng: String,
    var regionNameKr: String,
    var regionNameEng: String
){
    constructor(grape: Grape, wineEx: Wine) : this(
        grapeId = grape.grapeId,
        nameKr = grape.nameKr,
        nameEng = grape.nameEng,
        regionNameKr= wineEx.regionNameKr,
        regionNameEng = wineEx.regionNameKr
    )
}

