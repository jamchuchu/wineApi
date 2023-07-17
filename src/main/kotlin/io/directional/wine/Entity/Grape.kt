package io.directional.wine.Entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class Grape (
    var grapeId: Int,
    var nameKr :String,
    var nameEng: String,
    var acidity: Int,
    var body: Int,
    var sweetness: Int,
    var tannin: Int
    )