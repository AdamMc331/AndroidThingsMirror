package com.adammcneilly.magicmirror

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityCondition")
fun View.visibleIf(condition: Boolean?) {
    this.visibility = if (condition == true) View.VISIBLE else View.GONE
}

@BindingAdapter("imageRes")
fun ImageView.imageResource(resource: Int?) {
    resource?.let(this::setImageResource)
}