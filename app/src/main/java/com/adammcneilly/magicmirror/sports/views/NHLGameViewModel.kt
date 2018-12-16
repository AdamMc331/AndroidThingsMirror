package com.adammcneilly.magicmirror.sports.views

import androidx.databinding.BaseObservable
import com.adammcneilly.magicmirror.sports.models.NHLGame
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class NHLGameViewModel : BaseObservable() {

    var game: NHLGame? = null
        set(value) {
            field = value
            notifyChange()
        }

    val gameDescription: String
        get() = "${game?.home?.alias} vs ${game?.away?.alias}"

    val gameTime: String
        get() {
            return LocalDateTime.parse(game?.scheduled, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .atOffset(ZoneOffset.UTC)
                    .atZoneSameInstant(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("hh:mm a"))
        }
}