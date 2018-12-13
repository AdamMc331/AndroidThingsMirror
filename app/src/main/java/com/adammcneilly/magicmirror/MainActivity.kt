package com.adammcneilly.magicmirror

import android.app.Activity
import android.os.Bundle
import com.adammcneilly.magicmirror.weather.models.ForecastData
import com.adammcneilly.magicmirror.weather.views.ForecastCellView

/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * val service = PeripheralManagerService()
 * val mLedGpio = service.openGpio("BCM6")
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * mLedGpio.value = true
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 *
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastCell = findViewById<ForecastCellView>(R.id.forecast_cell)
        forecastCell.bindModel(ForecastData(temperatureHigh = 30F, icon = "clear-day"))
    }
}
