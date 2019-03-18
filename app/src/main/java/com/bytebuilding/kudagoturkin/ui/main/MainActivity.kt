package com.bytebuilding.kudagoturkin.ui.main

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.databinding.ActivityMainBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity
import com.bytebuilding.kudagoturkin.utils.getDefaultCity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    private var mCityName: AppCompatTextView? = null

    override fun layoutId(): Int = R.layout.activity_main

    override fun initViews() {
        mCityName = findViewById(R.id.cityName)
    }

    override fun initListeners() {
    }

    override fun removeListeners() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCityName?.let { view ->
            view.text = getDefaultCity()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
