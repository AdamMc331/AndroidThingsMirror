package com.adammcneilly.magicmirror

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.magicmirror.sports.data.SportsRepository
import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.weather.data.WeatherRepository
import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.time.Instant

class MainActivityViewModel(private val weatherRepository: WeatherRepository, private val sportsRepository: SportsRepository) : ViewModel() {
    private val _forecastResponse = MutableLiveData<ForecastResponse>()
    val forecastResponse: LiveData<ForecastResponse> = _forecastResponse

    private val _nhlSchedule = MutableLiveData<NHLSchedule>()
    val nhlSchedule: LiveData<NHLSchedule> = _nhlSchedule

    private val compositeDisposable = CompositeDisposable()

    private val _state = MutableLiveData<MirrorState>()
    val state: LiveData<MirrorState> = _state

    private val currentState: MirrorState
        get() = _state.value ?: MirrorState()

    fun getForecast() {
        val disposable = weatherRepository.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processForecast, Timber::e)

        compositeDisposable.add(disposable)
    }

    fun getNhlSchedule() {
        val today = Instant.now()

        val disposable = sportsRepository.getNHLSchedule(today)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processNHLSchedule, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun processForecast(forecastResponse: ForecastResponse) {
        _state.value = currentState.copy(forecastResponse = forecastResponse)
    }

    private fun processNHLSchedule(nhlSchedule: NHLSchedule) {
        _state.value = currentState.copy(nhlSchedule = nhlSchedule)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}