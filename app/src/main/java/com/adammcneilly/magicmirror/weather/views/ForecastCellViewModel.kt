package com.adammcneilly.magicmirror.weather.views

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adammcneilly.magicmirror.weather.models.ForecastData
import com.adammcneilly.magicmirror.weather.models.Icon

class ForecastCellViewModel : BaseObservable() {
    var forecastData: ForecastData? = null
        set(value) {
            field = value
            notifyChange()
        }

    val iconRes: Int
        @Bindable get() = Icon.findFromString(forecastData?.icon.orEmpty()).iconRes

    val temperature: String
        @Bindable get() = "${forecastData?.temperatureHigh?.toInt()}°F"

    //TODO: Actually calculate date
    val date: String
        @Bindable get() = "Tomorrow"

    val showDate: Boolean
        @Bindable get() = true
}