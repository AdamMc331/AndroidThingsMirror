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
import com.adammcneilly.magicmirror.weather.data.DarkSkyAPI
import com.adammcneilly.magicmirror.weather.data.WeatherRepository
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The activity that runs the mirror.
 *
 * Everything on the mirror is displayed in a EpoxyRecyclerView managed by [MirrorController].
 */
class MainActivity : FragmentActivity() {
    private val mirrorController = MirrorController()

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

        setupRecyclerView()
        listenForData()
        requestData()
    }

    private fun requestData() {
        viewModel.beginRequestingData()
    }

    private fun listenForData() {
        viewModel.state.observe(this, Observer {
            mirrorController.setData(it)
        })
    }

    private fun setupRecyclerView() {
        mirror_recycler_view.layoutManager = LinearLayoutManager(this)
        mirror_recycler_view.setController(mirrorController)
    }
}
