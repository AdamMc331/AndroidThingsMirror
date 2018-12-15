package com.adammcneilly.magicmirror.weather.views

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import com.adammcneilly.magicmirror.weather.models.Icon

class ForecastCellViewModel : BaseObservable() {
    var forecastResponse: ForecastResponse? = null
        set(value) {
            field = value
            notifyChange()
        }

    val iconRes: Int
        @Bindable get() = Icon.findFromString(forecastResponse?.currently?.icon.orEmpty()).iconRes

    val temperature: String
        @Bindable get() = "${forecastResponse?.currently?.temperature?.toInt()}°F"

    val summary: String
        @Bindable get() = forecastResponse?.hourly?.summary.orEmpty()
}