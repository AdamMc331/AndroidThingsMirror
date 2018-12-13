package com.adammcneilly.magicmirror.weather.models

import com.squareup.moshi.Json

data class ForecastFlags(
        val sources: List<String>? = null,
        @field:Json(name = "nearest-station") val nearestStation: Float? = null,
        val units: String? = null
)