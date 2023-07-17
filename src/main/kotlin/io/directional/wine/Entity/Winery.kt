package io.directional.wine.Entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class Winery (
    var wineryId: Int,
    var nameKr :String,
    var nameEng: String,
    var regionNameKr: String,
    var regionNameEng: String
)