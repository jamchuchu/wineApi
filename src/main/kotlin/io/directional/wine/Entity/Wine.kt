package io.directional.wine.Entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class Wine(
    var wineId: Int,
    var type: String,
    var nameKr: String,
    var nameEng: String,
    var alcohol: Long,
    var acidity: Int,
    var body: Int,
    var sweetness: Int,
    var tannin: Int,
    var servingTemperature: Int,
    var score: Long,
    var price: Int,
    var style: String,
    var grade: String,
    var importer: String,
    var wineryNameKr: String,
    var wineryNameEng: String,
    var regionNameKr: String,
    var regionNameEng: String
)

