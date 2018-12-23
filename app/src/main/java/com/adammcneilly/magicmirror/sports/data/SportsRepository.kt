package com.adammcneilly.magicmirror.sports.data

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import io.reactivex.Single
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class SportsRepository(private val api: SportRadarAPI) {

    fun getNHLSchedule(date: Instant = Instant.now()): Single<NHLSchedule> {
        val localDateTime = LocalDateTime.ofInstant(date, ZoneId.of("EST"))

        return api.getNhlScheduleForDate(
                localDateTime.year.toString(),
                localDateTime.monthValue.toString(),
                localDateTime.dayOfMonth.toString()
        )
    }
}