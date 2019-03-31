package com.bytebuilding.kudagoturkin.ui.main

import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.databinding.ActivityMainBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity
import com.bytebuilding.kudagoturkin.ui.cities.ChooseCityActivity
import com.bytebuilding.kudagoturkin.utils.launchActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    private var mCityPickerContainer: LinearLayoutCompat? = null
    private var mCityName: AppCompatTextView? = null

    override fun layoutId(): Int = R.layout.activity_main

    override fun initViews() {
        mCityName = findViewById(R.id.cityName)
        mCityPickerContainer = findViewById(R.id.cityPickerContainer)
    }

    override fun initListeners() {
        mCityPickerContainer?.setOnClickListener { view ->
            launchActivity<ChooseCityActivity> { }
        }
    }

    override fun observeChanges() {
        mViewModel.getDefaultCityName()
    }

    override fun removeListeners() {
        mCityPickerContainer?.setOnClickListener(null)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
