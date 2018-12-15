package com.adammcneilly.magicmirror.sports.data

import com.adammcneilly.magicmirror.day
import com.adammcneilly.magicmirror.month
import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.year
import io.reactivex.Single
import java.util.*

class SportsRepository(private val api: SportRadarAPI) {

    fun getNHLSchedule(date: Date): Single<NHLSchedule> {
        return api.getNhlScheduleForDate(
                date.year().toString(),
                date.month().toString(),
                date.day().toString()
        )
    }
}