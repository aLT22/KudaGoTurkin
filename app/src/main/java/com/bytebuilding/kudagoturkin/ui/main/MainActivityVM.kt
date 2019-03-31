package com.bytebuilding.kudagoturkin.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bytebuilding.kudagoturkin.ui.base.BaseViewModel
import com.bytebuilding.kudagoturkin.utils.getDefaultCityName
import kotlinx.coroutines.launch


class MainActivityVM(private val context: Application) : BaseViewModel() {

    /**
     * ViewDataBinding LiveData
     * */
    val mDefaultCityName = MutableLiveData<String>()

    init {
        launch {
            mDefaultCityName.postValue(context.getDefaultCityName())
        }
    }

    fun getDefaultCityName() =
        launch {
            mDefaultCityName.postValue(context.getDefaultCityName())
        }

}