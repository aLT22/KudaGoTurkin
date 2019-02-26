package com.bytebuilding.kudagoturkin.ui.main

import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.databinding.ActivityMainBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initViews() {
    }

    override fun initListeners() {
    }

    override fun removeListeners() {
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
