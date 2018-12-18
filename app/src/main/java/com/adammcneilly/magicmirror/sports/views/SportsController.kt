package com.adammcneilly.magicmirror.sports.views

import com.adammcneilly.magicmirror.sports.data.SportsState
import com.airbnb.epoxy.TypedEpoxyController

class SportsController : TypedEpoxyController<SportsState>() {
    override fun buildModels(data: SportsState?) {
        if (data?.nhlSchedule?.games?.isNotEmpty() == true) {
            LeagueHeaderViewModel_()
                    .id("NHL")
                    .text("NHL")
                    .addTo(this)
        }

        data?.nhlSchedule?.games?.forEach { game ->
            NHLGameViewModel_()
                    .id(game.id)
                    .model(game)
                    .addTo(this)
        }
    }
}