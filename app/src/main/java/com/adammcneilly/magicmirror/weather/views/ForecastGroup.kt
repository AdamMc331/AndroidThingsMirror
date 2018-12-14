package com.adammcneilly.magicmirror.weather.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.adammcneilly.magicmirror.weather.models.ForecastData

class ForecastGroup @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun getOrientation(): Int {
        return LinearLayout.HORIZONTAL
    }

    /**
     * TODO: Make the num of forecast items a property that can be configured.
     */
    fun bindData(data: List<ForecastData>?) {
        removeAllViews()

        data?.take(5)?.forEach {
            val newView = ForecastCellView(context)
            newView.layoutParams = SINGLE_WEIGHT_PARAMS
            newView.bindModel(it)
            addView(newView)
        }
    }

    companion object {
        private val SINGLE_WEIGHT_PARAMS = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1F)
    }
}