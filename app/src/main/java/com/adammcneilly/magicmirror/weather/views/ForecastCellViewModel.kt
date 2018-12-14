package com.adammcneilly.magicmirror.weather.views

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adammcneilly.magicmirror.weather.models.ForecastData
import com.adammcneilly.magicmirror.weather.models.Icon
import java.util.*

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

    val date: String
        @Bindable get() {
            val time = forecastData?.time ?: return ""

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time * 1000L

            return when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SUNDAY -> "Sunday"
                Calendar.MONDAY -> "Monday"
                Calendar.TUESDAY -> "Tuesday"
                Calendar.WEDNESDAY -> "Wednesday"
                Calendar.THURSDAY -> "Thursday"
                Calendar.FRIDAY -> "Friday"
                else -> "Saturday"
            }
        }

    val showDate: Boolean
        @Bindable get() {
            val time = forecastData?.time ?: return false

            val forecastDate = Date()
            forecastDate.time = time * 1000L

            val today = Date()
            return !today.isEqualTo(forecastDate)
        }
}

fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Date.isEqualTo(otherDate: Date): Boolean {
    val calendar1 = this.toCalendar()
    val calendar2 = otherDate.toCalendar()

    return calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
            && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
            && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
}