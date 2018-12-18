package com.adammcneilly.magicmirror

import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.sports.views.LeagueHeaderViewModel_
import com.adammcneilly.magicmirror.sports.views.NHLGameViewModel_
import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import com.adammcneilly.magicmirror.weather.views.ForecastCellViewModel_
import com.airbnb.epoxy.TypedEpoxyController

class MirrorController : TypedEpoxyController<MirrorState>() {
    override fun buildModels(data: MirrorState?) {
        processForecast(data?.forecastResponse)
        processNHLSchedule(data?.nhlSchedule)
    }

    private fun processForecast(forecastResponse: ForecastResponse?) {
        forecastResponse?.let { forecast ->
            ForecastCellViewModel_()
                    .id("Forecast")
                    .model(forecast)
                    .addTo(this)
        }
    }

    private fun processNHLSchedule(nhlSchedule: NHLSchedule?) {
        val hasGames = nhlSchedule?.games?.isNotEmpty() == true

        if (hasGames) {
            LeagueHeaderViewModel_()
                    .id("NHL")
                    .text("NHL")
                    .addTo(this)

            nhlSchedule?.games?.forEach { game ->
                NHLGameViewModel_()
                        .id("NHL Game: ${game.id}")
                        .model(game)
                        .addTo(this)
            }
        }
    }
}