package io.directional.wine.Dto

import io.directional.wine.Entity.Wine

class WineDtoForSingle (
    var wineId: Int,
    var type: String,
    var nameKr: String,
    var nameEng: String,
    var alcohol: Long,
    var acidity: Int,
    var body: Int,
    var tannin: Int,
    var score: Long,
    var price: Int,
    var style: String,
    var grade: String,
    var importer: String,
    var wineryNameEng: String,
    var regionNameEng: String,
    var allRegions: List<String>,
    var aroma: String,
    var pairing: String,
    var grape: List<String>
) {
    constructor(wine: Wine, allRegions:List<String>,
    aroma: String, pairing: String, grape: List<String>) : this(
        wineId = wine.wineId,
        type = wine.type,
        nameKr = wine.nameKr,
        nameEng = wine.nameEng,
        alcohol = wine.alcohol,
        acidity = wine.acidity,
        body = wine.body,
        tannin = wine.tannin,
        score = wine.score,
        price = wine.price,
        style = wine.style,
        grade = wine.grade,
        importer =wine.importer,
        wineryNameEng = wine.wineryNameEng,
        regionNameEng = wine.regionNameEng,
        allRegions = allRegions,
        aroma = aroma,
        pairing = pairing,
        grape = grape
    )
}
