package com.adammcneilly.magicmirror.sports.models

import com.squareup.moshi.Json

data class NHLTeam(
        val id: String? = null,
        val name: String? = null,
        val alias: String? = null,
        @Json(name = "sr_id") val srId: String? = null,
        val reference: String? = null
)