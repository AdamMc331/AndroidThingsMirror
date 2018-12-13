package com.adammcneilly.magicmirror.weather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyAPI {
    @GET("forecast/{key}/{latitude},{longitude}")
    fun getForecast(
            @Path("latitude") latitude: String,
            @Path("longitude") longitude: String,
            @Path("key") key: String = Credentials.DARK_SKY_KEY
    ): Single<ForecastResponse>
}