package com.bytebuilding.kudagoturkin.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.kudagoturkin.data.model.EventItem
import com.bytebuilding.kudagoturkin.ui.base.BaseViewModel
import com.bytebuilding.kudagoturkin.utils.DEFAULT_CHARSET
import com.bytebuilding.kudagoturkin.utils.getDefaultCityName
import com.bytebuilding.kudagoturkin.utils.loge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.nio.charset.Charset
import java.util.*


class MainActivityVM(private val context: Application) : BaseViewModel() {

    val mEventsLiveData = MutableLiveData<LinkedList<EventItem>>()

    /**
     * ViewDataBinding LiveData
     * */
    val mDefaultCityName = MutableLiveData<String>()
    val mIsLoading = MutableLiveData<Boolean>()

    init {
        mIsLoading.value = false
        getEvents()
        getDefaultCityName()
    }

    fun getDefaultCityName() {
        mDefaultCityName.value = context.getDefaultCityName()
    }

    fun getEvents(): LiveData<LinkedList<EventItem>> {
        launch {
            try {
                mIsLoading.postValue(true)

                val inputStream = context.assets.open(ASSET_EVENTS)
                val inputStreamSize = inputStream.available()
                val buffer = ByteArray(inputStreamSize)

                inputStream.read(buffer)

                inputStream.close()

                val events = String(buffer, Charset.forName(DEFAULT_CHARSET))

                val listType = object : TypeToken<LinkedList<EventItem.Event>>() {}.type

                val eventsList = Gson().fromJson<LinkedList<EventItem>>(events, listType)
                eventsList.add(0, EventItem.EventHeader())

                mEventsLiveData.postValue(eventsList)

                mIsLoading.postValue(false)
            } catch (th: Throwable) {
                mIsLoading.postValue(false)
                loge(TAG, "Something went wrong while fetching events from assets", th)
            }
        }

        return mEventsLiveData
    }

    companion object {
        const val TAG = "MainActivityVM"

        const val ASSET_EVENTS = "events.json"
    }

}