package com.adammcneilly.magicmirror.weather.data

import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface that communicates with the Dark Sky API.
 */
interface DarkSkyAPI {
    @GET("forecast/{key}/{latitude},{longitude}")
    fun getForecast(
            @Path("latitude") latitude: String,
            @Path("longitude") longitude: String,
            @Path("key") key: String = Credentials.DARK_SKY_KEY
    ): Single<ForecastResponse>

    companion object {
        private const val BASE_URL = "https://api.darksky.net"

        fun getDefaultAPI(): DarkSkyAPI {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                    .build()
                    .create(DarkSkyAPI::class.java)
        }
    }
}