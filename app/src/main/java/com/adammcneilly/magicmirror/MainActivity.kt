package com.adammcneilly.magicmirror

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.magicmirror.weather.data.DarkSkyAPI
import com.adammcneilly.magicmirror.weather.data.WeatherRepository
import kotlinx.android.synthetic.main.activity_main.*

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
class MainActivity : FragmentActivity() {

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val api = DarkSkyAPI.getDefaultAPI()
            val repository = WeatherRepository(api)

            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        requestData()
        listenForData()
    }

    private fun requestData() {
        viewModel.getForecast()
    }

    private fun listenForData() {
        viewModel.forecastResponse.observe(this, Observer {
            forecast_cell.bindModel(it)
        })
    }
}
