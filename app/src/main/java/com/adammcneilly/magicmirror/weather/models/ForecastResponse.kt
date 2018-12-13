package com.adammcneilly.magicmirror.weather.models

data class ForecastResponse(
        val latitude: Float? = null,
        val longitude: Float? = null,
        val timezone: String? = null,
        val currently: ForecastData? = null,
        val minutely: Forecast? = null,
        val hourly: Forecast? = null,
        val daily: Forecast? = null,
        val flags: ForecastFlags? = null,
        val offset: Int? = null
)