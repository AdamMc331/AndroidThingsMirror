package com.adammcneilly.magicmirror

import java.time.*
import java.time.format.DateTimeFormatter

/**
 * The timezone of whoever is running this smart mirror app. This was added so that anyone who
 * clones this project could easily modify it.
 */
const val timeZone = "EST"

/**
 * Get the current ZonedDateTime for the given [timeZone].
 */
fun currentZonedDateTime(): ZonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(timeZone))

/**
 * Given a string and a formatter, convert it to a ZonedDateTime for our given [timeZone].
 */
fun String.asZonedDateTime(formatter: DateTimeFormatter): ZonedDateTime = LocalDateTime.parse(this, formatter)
        .atOffset(ZoneOffset.UTC)
        .atZoneSameInstant(ZoneId.of(timeZone))
