package com.bytebuilding.kudagoturkin.ui.cities

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.kudagoturkin.data.model.City
import com.bytebuilding.kudagoturkin.ui.base.BaseViewModel
import com.bytebuilding.kudagoturkin.utils.ASSET_CITIES
import com.bytebuilding.kudagoturkin.utils.DEFAULT_CHARSET
import com.bytebuilding.kudagoturkin.utils.SET_CITY_TAG
import com.bytebuilding.kudagoturkin.utils.loge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.nio.charset.Charset


class ChooseCityActivityVM(private val context: Application) : BaseViewModel() {

    private val mCitiesLiveData = MutableLiveData<List<City>>()

    init {
        getCities()
    }

    fun getCities(): LiveData<List<City>> {
        launch {
            try {
                val inputStream = context.assets.open(ASSET_CITIES)
                val inputStreamSize = inputStream.available()
                val buffer = ByteArray(inputStreamSize)

                inputStream.read(buffer)

                inputStream.close()

                val cities = String(buffer, Charset.forName(DEFAULT_CHARSET))

                val listType = object : TypeToken<List<City>>() {}.type

                mCitiesLiveData.postValue(Gson().fromJson<List<City>>(cities, listType))
            } catch (th: Throwable) {
                loge(SET_CITY_TAG, "Something went wrong while fetching cities from assets", th)
            }
        }

        return mCitiesLiveData
    }
}