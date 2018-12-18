package com.adammcneilly.magicmirror

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.magicmirror.sports.data.SportRadarAPI
import com.adammcneilly.magicmirror.sports.data.SportsRepository
import com.adammcneilly.magicmirror.sports.data.SportsState
import com.adammcneilly.magicmirror.sports.views.NHLGameAdapter
import com.adammcneilly.magicmirror.sports.views.SportsController
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
    private val sportsController = SportsController()

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val weatherAPI = DarkSkyAPI.getDefaultAPI()
            val weatherRepository = WeatherRepository(weatherAPI)

            val sportsAPI = SportRadarAPI.getDefaultAPI()
            val sportsRepository = SportsRepository(sportsAPI)

            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(weatherRepository, sportsRepository) as T
        }
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        setupNHLRecyclerView()

        listenForData()
        requestData()
    }

    private fun requestData() {
        viewModel.getForecast()
        viewModel.getNhlSchedule()
    }

    private fun listenForData() {
        viewModel.forecastResponse.observe(this, Observer {
            forecast_cell.bindModel(it)
        })

        viewModel.nhlSchedule.observe(this, Observer {
            val sportsState = SportsState(nhlSchedule = it)
            sportsController.setData(sportsState)
        })
    }

    private fun setupNHLRecyclerView() {
        nhl_recycler_view.layoutManager = LinearLayoutManager(this)
        nhl_recycler_view.setController(sportsController)
    }
}
