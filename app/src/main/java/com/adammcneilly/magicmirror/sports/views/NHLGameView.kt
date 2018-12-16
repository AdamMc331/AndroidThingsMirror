package com.adammcneilly.magicmirror.sports.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.adammcneilly.magicmirror.databinding.NhlGameViewBinding
import com.adammcneilly.magicmirror.sports.models.NHLGame

class NHLGameView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = NhlGameViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val viewModel = NHLGameViewModel()

    init {
        binding.viewModel = viewModel
    }

    fun bindModel(game: NHLGame?) {
        viewModel.game = game
    }
}