package com.adammcneilly.magicmirror.weather.models

import com.adammcneilly.magicmirror.R

enum class Icon(val serverString: String, val iconRes: Int) {
    CLEAR_DAY("clear-day", R.drawable.ic_sunny_white_24dp),
    CLEAR_NIGHT("clear-night", R.drawable.ic_sunny_white_24dp),
    RAIN("rain", R.drawable.ic_rainy_white_24dp),
    SNOW("snow", R.drawable.ic_snowy_white_24dp),
    SLEET("sleet", R.drawable.ic_hail_white_24dp),
    WIND("wind", R.drawable.ic_windy_white_24dp),
    FOG("fog", R.drawable.ic_fog_white_24dp),
    CLOUDY("cloudy", R.drawable.ic_cloudy_white_24dp),
    PARTLY_CLOUDY_DAY("partly-cloudy-day", R.drawable.ic_partly_cloudy_white_24dp),
    PARTLY_CLOUDY_NIGHT("partly-cloudy-night", R.drawable.ic_partly_cloudy_white_24dp),
    DEFAULT("", R.drawable.ic_help_outline_white_24dp);

    companion object {
        fun findFromString(serverString: String): Icon {
            return values().firstOrNull { it.serverString == serverString } ?: DEFAULT
        }
    }
}