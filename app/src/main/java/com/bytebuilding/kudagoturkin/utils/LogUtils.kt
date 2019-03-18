package com.bytebuilding.kudagoturkin.utils

import android.util.Log
import com.bytebuilding.kudagoturkin.BuildConfig

//Defaults
const val DEFAULT_TAG = "Debug"
const val DEFAULT_MESSAGE = "msg"

fun logd(
    tag: CharSequence = DEFAULT_TAG,
    msg: CharSequence = DEFAULT_MESSAGE,
    th: Throwable? = null
) {
    if (BuildConfig.DEBUG) {
        Log.d(tag.toString(), msg.toString(), th)
    }
}

fun loge(
    tag: CharSequence = DEFAULT_TAG,
    msg: CharSequence = DEFAULT_MESSAGE,
    th: Throwable? = null
) {
    if (BuildConfig.DEBUG) {
        Log.e(tag.toString(), msg.toString(), th)
    }
}