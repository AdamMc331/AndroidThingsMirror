package com.adammcneilly.magicmirror.date

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.adammcneilly.magicmirror.databinding.LayoutDateTimeBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CurrentDateTimeView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = LayoutDateTimeBinding.inflate(LayoutInflater.from(context), this, true)
    private val viewModel = CurrentDateTimeViewModel()
    private val compositeDisposable = CompositeDisposable()

    init {
        binding.viewModel = viewModel
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        listenForTimeUpdates()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        compositeDisposable.clear()
    }

    private fun listenForTimeUpdates() {
        val disposable = Observable.interval(1, TimeUnit.SECONDS)
                .startWith(0)
                .subscribe({ viewModel.notifyChange() }, Timber::e)

        compositeDisposable.add(disposable)
    }
}