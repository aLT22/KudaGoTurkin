package com.bytebuilding.kudagoturkin.utils

import android.content.res.TypedArray
import androidx.annotation.StyleableRes


fun TypedArray.getString(
    @StyleableRes index: Int,
    defaultValue: String
): String = getString(index) ?: defaultValue