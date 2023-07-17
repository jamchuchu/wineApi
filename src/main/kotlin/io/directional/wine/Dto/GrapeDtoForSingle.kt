import io.directional.wine.Entity.Grape
import io.directional.wine.Entity.Wine

data class GrapeDtoForSingle (
    var grapeId: Int,
    var nameKr: String,
    var nameEng: String,
    var acidity: Int,
    var body: Int,
    var sweetness: Int,
    var tannin: Int,
    var regionNameKr: String,
    var regionNameEng: String,
    var wines: List<Wine>
){
    constructor(grape: Grape, wines: List<Wine>) : this(
        grapeId = grape.grapeId,
        nameKr = grape.nameKr,
        nameEng = grape.nameEng,
        acidity = grape.acidity,
        body = grape.body,
        sweetness = grape.sweetness,
        tannin = grape.tannin,
        regionNameKr= wines.get(0).regionNameKr,
        regionNameEng = wines.get(0).regionNameEng,
        wines = wines
    )
}