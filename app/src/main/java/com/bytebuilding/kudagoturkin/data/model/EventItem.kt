package com.bytebuilding.kudagoturkin.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class EventItem {

    val id: Long = -1L

    @Parcelize
    data class Event(
        val title: String = "",
        val description: String = "",
        val location: String = "",
        val dateTime: String = "",
        val price: String = ""
    ) : EventItem(), Parcelable

    @Parcelize
    data class EventHeader(
        val headerText: String = ""
    ) : EventItem(), Parcelable

}