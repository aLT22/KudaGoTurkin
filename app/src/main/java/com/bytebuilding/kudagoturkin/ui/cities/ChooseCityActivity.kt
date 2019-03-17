package com.bytebuilding.kudagoturkin.ui.cities

import androidx.databinding.ViewDataBinding
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity


class ChooseCityActivity : BaseActivity<ViewDataBinding, ChooseCityActivityVM>(ChooseCityActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_choose_city

    override fun initViews() {
    }

    override fun initListeners() {
    }

    override fun removeListeners() {
    }

    companion object {
        const val TAG = "ChooseCityA"
    }
}