package com.bytebuilding.kudagoturkin.di

import com.bytebuilding.kudagoturkin.ui.cities.ChooseCityActivityVM
import com.bytebuilding.kudagoturkin.ui.main.MainActivityVM
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module {

    /**
     * Activity VMs
     * */
    viewModel { MainActivityVM() }
    viewModel { ChooseCityActivityVM(androidApplication()) }

}