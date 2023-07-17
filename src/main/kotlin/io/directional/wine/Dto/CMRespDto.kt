package io.directional.wine.Dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Data
data class CMRespDto<T> (
    var code: Int,
    var message: String,
    var data: T
)