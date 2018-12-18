package com.adammcneilly.magicmirror

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.weather.models.ForecastResponse

data class MirrorState(
        val forecastResponse: ForecastResponse? = null,
        val nhlSchedule: NHLSchedule? = null
)