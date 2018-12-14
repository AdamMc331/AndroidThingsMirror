package com.adammcneilly.magicmirror.weather.models

data class Forecast(
        val summary: String? = null,
        val icon: String? = null,
        val data: List<ForecastData>? = null
)