package com.adammcneilly.magicmirror.weather

data class Forecast(
        val summary: String? = null,
        val icon: String? = null,
        val data: List<Forecast>? = null
)