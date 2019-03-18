package com.bytebuilding.kudagoturkin.ui.cities

import androidx.appcompat.widget.AppCompatImageView
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.databinding.ActivityChooseCityBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity


class ChooseCityActivity : BaseActivity<ActivityChooseCityBinding, ChooseCityActivityVM>(ChooseCityActivityVM::class) {

    private var mClose: AppCompatImageView? = null

    override fun layoutId(): Int = R.layout.activity_choose_city

    override fun initViews() {
        mClose = findViewById(R.id.close)
    }

    override fun initListeners() {
        mClose?.setOnClickListener {
            finish()
        }
    }

    override fun removeListeners() {
        mClose?.setOnClickListener(null)
    }

    companion object {
        const val TAG = "ChooseCityA"
    }
}