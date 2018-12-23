package com.adammcneilly.magicmirror.sports.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.adammcneilly.magicmirror.databinding.LeagueHeaderViewBinding
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

/**
 * Custom view that is intended to display a header for a sports league (NHL, NBA, MLB).
 *
 * Really, this is just a fancy container for a text view.
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LeagueHeaderView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = LeagueHeaderViewBinding.inflate(LayoutInflater.from(context), this, true)

    var text: String? = null
        @ModelProp set(value) {
            field = value
            binding.leagueText.text = value
        }
}