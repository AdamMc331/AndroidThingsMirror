package com.adammcneilly.magicmirror

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.weather.models.ForecastResponse

/**
 * State class handles the values of everything that will be on screen at once.
 */
data class MirrorState(
        val forecastResponse: ForecastResponse? = null,
        val nhlSchedule: NHLSchedule? = null
)