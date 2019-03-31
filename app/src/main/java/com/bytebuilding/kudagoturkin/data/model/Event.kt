package com.bytebuilding.kudagoturkin.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Long? = -1L,
    val title: CharSequence = "",
    val description: CharSequence = "",
    val location: CharSequence = "",
    val dateTime: CharSequence = "",
    val price: CharSequence = ""
) : Parcelable