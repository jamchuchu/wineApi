package io.directional.wine.Entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class Region (
    var regionId: Int,
    var regionNameKr: String,
    var regionNameEng: String,
    var parentRegionNameKr: String?,
    var parentRegionNameEng: String?
        )