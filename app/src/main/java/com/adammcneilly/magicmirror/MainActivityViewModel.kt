package com.adammcneilly.magicmirror

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.magicmirror.sports.data.SportsRepository
import com.adammcneilly.magicmirror.sports.models.NHLSchedule
import com.adammcneilly.magicmirror.weather.data.WeatherRepository
import com.adammcneilly.magicmirror.weather.models.ForecastResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(private val weatherRepository: WeatherRepository, private val sportsRepository: SportsRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _state = MutableLiveData<MirrorState>()
    val state: LiveData<MirrorState> = _state

    fun loadData() {
        val forecastRequest = weatherRepository.getForecast()
        val nhlScheduleRequest = sportsRepository.getNHLSchedule()

        val disposable = Single.zip(forecastRequest, nhlScheduleRequest, io.reactivex.functions.BiFunction<ForecastResponse, NHLSchedule, MirrorState> { forecastResponse, nhlSchedule ->
            return@BiFunction MirrorState(forecastResponse = forecastResponse, nhlSchedule = nhlSchedule)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_state::setValue, Timber::e)

        compositeDisposable.add(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}