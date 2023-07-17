package io.directional.wine.Dto

import io.directional.wine.Entity.Wine

data class WineDtoForMulti(
    var wineId: Int,
    var type: String,
    var nameKr: String,
    var nameEng: String,
    var topRegionNameEng: String
) {
    constructor(wineAllInfo: Wine, topRegionNameEng:String) : this(
        wineId = wineAllInfo.wineId,
        type = wineAllInfo.type,
        nameKr = wineAllInfo.nameKr,
        nameEng = wineAllInfo.nameEng,
        topRegionNameEng = topRegionNameEng
    )
}