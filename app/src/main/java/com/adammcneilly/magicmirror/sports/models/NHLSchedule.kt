package com.adammcneilly.magicmirror.sports.models

data class NHLSchedule(
        val date: String? = null,
        val games: List<NHLGame>? = null
)