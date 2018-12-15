package com.adammcneilly.magicmirror

import java.util.*

fun Date.asCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Date.year(): Int {
    return this.asCalendar().get(Calendar.YEAR)
}

fun Date.month(): Int {
    return this.asCalendar().get(Calendar.MONTH)
}

fun Date.day(): Int {
    return this.asCalendar().get(Calendar.DAY_OF_MONTH)
}