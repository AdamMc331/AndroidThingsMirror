package com.adammcneilly.magicmirror.sports.models

import com.squareup.moshi.Json

data class NHLGame(
        val id: String? = null,
        val status: String? = null,
        val scheduled: String? = null,
        @Json(name = "sr_id") val srId: String? = null,
        val reference: String? = null,
        val home: NHLTeam? = null,
        val away: NHLTeam? = null
)