package com.adammcneilly.magicmirror.sports.views

import androidx.databinding.BaseObservable
import com.adammcneilly.magicmirror.asZonedDateTime
import com.adammcneilly.magicmirror.sports.models.NHLGame
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * ViewModel responsible for formatting how an NHL game is played.
 */
class NHLGameViewModel : BaseObservable() {

    var game: NHLGame? = null
        set(value) {
            field = value
            notifyChange()
        }

    /**
     * The game title displays which two teams are playing.
     */
    val gameTitle: String
        get() = "${game?.home?.alias} vs ${game?.away?.alias}"

    /**
     * A formatted version of the game's scheduled start time.
     */
    private val startTime: String
        get() = game?.scheduled
                ?.asZonedDateTime(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                ?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                .orEmpty()

    /**
     * The score of the game, if available.
     */
    private val gameScore: String?
        get() {
            val homePoints = game?.home_points
            val awayPoints = game?.away_points

            val scoreString = "$homePoints - $awayPoints"

            return when {
                homePoints == null || awayPoints == null -> null
                else -> scoreString
            }
        }

    /**
     * If a game score is available, we show that. Otherwise, default to the start time.
     */
    val gameSubtitle: String
        get() = gameScore ?: startTime
}