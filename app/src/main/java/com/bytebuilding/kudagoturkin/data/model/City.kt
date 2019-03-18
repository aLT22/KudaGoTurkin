package com.bytebuilding.kudagoturkin.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class City(
        val id: Long = -1L,
        val name: String = ""
) : Parcelable