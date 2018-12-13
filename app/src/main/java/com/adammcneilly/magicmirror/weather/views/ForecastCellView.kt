package com.adammcneilly.magicmirror.weather.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.adammcneilly.magicmirror.databinding.ForecastCellBinding
import com.adammcneilly.magicmirror.weather.models.ForecastData

class ForecastCellView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ForecastCellBinding.inflate(LayoutInflater.from(context), this, true)
    private val viewModel = ForecastCellViewModel()

    init {
        binding.viewModel = viewModel
    }

    fun bindModel(forecastData: ForecastData?) {
        viewModel.forecastData = forecastData
    }
}