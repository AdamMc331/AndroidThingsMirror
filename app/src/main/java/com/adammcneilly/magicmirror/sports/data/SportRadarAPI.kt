package com.adammcneilly.magicmirror.sports.data

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportRadarAPI {

    @GET("nhl/trial/v6/en/games/{year}/{month}/{day}/schedule.json")
    fun getNhlScheduleForDate(
            @Path("year") year: String,
            @Path("month") month: String,
            @Path("day") day: String,
            @Query("api_key") key: String = Credentials.NHL_KEY
    ): Single<NHLSchedule>

    companion object {
        private const val BASE_URL = "https://api.sportradar.us"

        fun getDefaultAPI(): SportRadarAPI {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                    .build()
                    .create(SportRadarAPI::class.java)
        }
    }
}