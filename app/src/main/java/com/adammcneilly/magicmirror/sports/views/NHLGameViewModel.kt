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

    val gameTitle: String
        get() = "${game?.home?.alias} vs ${game?.away?.alias}"

    private val startTime: String
        get() = LocalDateTime.parse(game?.scheduled, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .atOffset(ZoneOffset.UTC)
                .atZoneSameInstant(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("hh:mm a"))

    private val gameScore: String?
        get() {
            val homePoints = game?.home_points
            val awayPoints = game?.away_points

            val scoreString = "$homePoints - $awayPoints"

            return when {
                homePoints == null || awayPoints == null -> null
                game?.status == "closed" || game?.status == "complete" -> "$scoreString F"
                else -> scoreString
            }
        }

    val gameSubtitle: String
        get() = gameScore ?: startTime
}