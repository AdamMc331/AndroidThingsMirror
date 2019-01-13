package com.adammcneilly.magicmirror.sports.data

import com.adammcneilly.magicmirror.currentZonedDateTime
import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import io.reactivex.Single
import java.time.LocalDateTime

/**
 * Repository class which handles some networking logic for the Sport Radar API.
 */
class SportsRepository(private val api: SportRadarAPI) {

    /**
     * Retrieves the NHL Schedule for any given day. Defaults to the current time if one is not supplied.
     */
    fun getNHLSchedule(date: LocalDateTime = currentZonedDateTime().toLocalDateTime()): Single<NHLSchedule> {

        return api.getNhlScheduleForDate(
                date.year.toString(),
                date.monthValue.toString(),
                date.dayOfMonth.toString()
        )
    }
}