package com.adammcneilly.magicmirror.sports.models

data class NHLGame(
        val id: String? = null,
        val status: String? = null,
        val scheduled: String? = null,
        val sr_id: String? = null,
        val reference: String? = null,
        val home: NHLTeam? = null,
        val away: NHLTeam? = null,
        val home_points: Int? = null,
        val away_points: Int? = null
)