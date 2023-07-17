package io.directional.wine.Dto

import io.directional.wine.Entity.Region

data class RegionDtoForSingle (
    var regionId: Int,
    var regionNameKr: String,
    var regionNameEng: String,
    var allParentRegions: List<String>,
    var grapeNames: List<String>,
    var wineryNames: List<String>,
    var wineNames: List<String>
        ){
    constructor(region: Region, allParentRegions: List<String>,
                grapeNames: List<String>,wineryNames: List<String>,wineNames: List<String>): this(
        regionId = region.regionId,
        regionNameKr = region.regionNameKr,
        regionNameEng = region.regionNameEng,
        allParentRegions = allParentRegions,
        grapeNames = grapeNames,
        wineryNames = wineryNames,
        wineNames = wineNames
    )
}

