package com.bytebuilding.kudagoturkin

import android.app.Application
import com.bytebuilding.kudagoturkin.di.viewModelModule
import org.koin.android.ext.android.startKoin


class KudaGoTurkinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                viewModelModule
            )
        )
    }

}