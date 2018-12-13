package com.adammcneilly.magicmirror.weather.data

import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import io.reactivex.Single

class WeatherRepository(private val api: DarkSkyAPI) {
    /**
     * Default is Brooklyn, NY.
     */
    fun getForecast(latitude: Float = 40.663540F, longitude: Float = -73.991044F): Single<ForecastResponse> {
        return api.getForecast(latitude.toString(), longitude.toString())
    }
}