package com.adammcneilly.magicmirror

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.sports.views.LeagueHeaderViewModel_
import com.adammcneilly.magicmirror.sports.views.NHLGameViewModel_
import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import com.adammcneilly.magicmirror.weather.views.ForecastCellViewModel_
import com.airbnb.epoxy.TypedEpoxyController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Epoxy Controller which defines the order in which items from our [MirrorState] appear.
 */
class MirrorController : TypedEpoxyController<MirrorState>() {
    override fun buildModels(data: MirrorState?) {
        processForecast(data?.forecastResponse)
        data?.nhlSchedules?.let(this::processNHLSchedules)
    }

    private fun processForecast(forecastResponse: ForecastResponse?) {
        forecastResponse?.let { forecast ->
            ForecastCellViewModel_()
                    .id("Forecast")
                    .model(forecast)
                    .addTo(this)
        }
    }

    private fun processNHLSchedules(schedules: List<NHLSchedule>) {
        val hasGames = schedules.all { it.games?.isNotEmpty() == true }

        if (hasGames) {
            LeagueHeaderViewModel_()
                    .id("NHL")
                    .text("NHL")
                    .addTo(this)

            schedules.forEach {
                LeagueHeaderViewModel_()
                        .id(it.date)
                        .text(it.date)
                        .addTo(this)

                it.games?.forEach { game ->
                    NHLGameViewModel_()
                            .id("NHL Game: ${game.id}")
                            .model(game)
                            .addTo(this)
                }
            }
        }
    }
}