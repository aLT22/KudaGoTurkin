package com.bytebuilding.kudagoturkin.ui.main

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity

class MainActivity : BaseActivity<ViewDataBinding, MainActivityVM>(MainActivityVM::class) {

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
