package com.adammcneilly.magicmirror.date

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CurrentDateTimeViewModel : BaseObservable() {
    private val currentDateTime: ZonedDateTime
        get() = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("EST"))

    val dateString: String
        @Bindable get() = currentDateTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))

    val timeString: String
        @Bindable get() = currentDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
}