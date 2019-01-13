package com.adammcneilly.magicmirror.date

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.adammcneilly.magicmirror.currentZonedDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * ViewModel responsible for formatting the current [dateString] and [timeString].
 */
class CurrentDateTimeViewModel : BaseObservable() {
    private val currentDateTime: ZonedDateTime
        get() = currentZonedDateTime()

    val dateString: String
        @Bindable get() = currentDateTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))

    val timeString: String
        @Bindable get() = currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
}