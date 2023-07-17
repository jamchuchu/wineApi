package io.directional.wine.Dto

import io.directional.wine.Entity.Wine
import io.directional.wine.Entity.Winery

class WineryDtoForSingle (
    var wineryId: Int,
    var nameKr :String,
    var nameEng: String,
    var regionNameKr: String,
    var regionNameEng: String,
    var wines: List<Wine>
    ){
    constructor(winery: Winery, wines: List<Wine>) : this(
        wineryId = winery.wineryId,
        nameKr = winery.nameKr,
        nameEng = winery.nameEng,
        regionNameKr= winery.regionNameKr,
        regionNameEng = winery.regionNameEng,
        wines = wines
    )
}