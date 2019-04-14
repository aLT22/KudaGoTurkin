package com.bytebuilding.kudagoturkin.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bytebuilding.kudagoturkin.data.model.City
import com.bytebuilding.kudagoturkin.ui.custom.EventInfoView


/**
 * AppCompatImageView
 * */
@BindingAdapter("bind:isCityChosen")
fun AppCompatImageView.isCityChosen(city: City) {
    this.visibility =
        if (context.getDefaultCity() == city) View.VISIBLE
        else View.GONE
}
/**
 * AppCompatImageView
 * */

/**
 * EventInfoView
 * */
@BindingAdapter("bind:eiv_description")
fun EventInfoView.setDescription(description: CharSequence) {
    setDescription(description)
}
/**
 * EventInfoView
 * */