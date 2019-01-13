package com.adammcneilly.magicmirror

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.magicmirror.sports.data.SportsRepository
import com.adammcneilly.magicmirror.weather.data.WeatherRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * The ViewModel class responsible for any network requests to pull the data we want to show on the mirror.
 */
class MainActivityViewModel(private val weatherRepository: WeatherRepository, private val sportsRepository: SportsRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _state = MutableLiveData<MirrorState>()
    val state: LiveData<MirrorState> = _state

    private val currentState: MirrorState
        get() = _state.value ?: MirrorState()

    /**
     * Since we want to load and display all data at once, we can zip the network requests together, and use
     * their responses to build a single state.
     */
    private fun loadData() {
        loadWeather()
    }

    private fun loadWeather() {
        val disposable = weatherRepository.getForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map currentState.copy(forecastResponse = it)
                }
                .doAfterSuccess { loadYesterdaysHockeySchedule() }
                .subscribe(this::handleState, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun loadYesterdaysHockeySchedule() {
        val yesterday = currentZonedDateTime().minusDays(1).toLocalDateTime()

        val disposable = sportsRepository.getNHLSchedule(yesterday)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    val currentSchedules = currentState.nhlSchedules.orEmpty()
                    return@map currentState.copy(nhlSchedules = currentSchedules + it)
                }
                .doAfterSuccess { loadTodaysHockeySchedule() }
                .subscribe(this::handleState, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun loadTodaysHockeySchedule() {
        val disposable = sportsRepository.getNHLSchedule()
                .delay(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    val currentSchedules = currentState.nhlSchedules.orEmpty()
                    return@map currentState.copy(nhlSchedules = currentSchedules + it)
                }
                .subscribe(this::handleState, Timber::e)

        compositeDisposable.add(disposable)
    }

    /**
     * Requests data every hour.
     */
    fun beginRequestingData() {
        val disposable = Observable.interval(60, TimeUnit.MINUTES)
                .startWith(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.loadData() }, Timber::e)

        compositeDisposable.add(disposable)
    }

    private fun handleState(newState: MirrorState) {
        this._state.value = newState
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}