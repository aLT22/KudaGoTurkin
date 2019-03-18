package com.bytebuilding.kudagoturkin.ui.cities

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.databinding.ActivityChooseCityBinding
import com.bytebuilding.kudagoturkin.ui.adapters.rv.ChooseCityListAdapter
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity
import com.bytebuilding.kudagoturkin.utils.setDefaultCity


class ChooseCityActivity : BaseActivity<ActivityChooseCityBinding, ChooseCityActivityVM>(ChooseCityActivityVM::class) {

    private var mClose: AppCompatImageView? = null

    private lateinit var mChooseCityAdapter: ChooseCityListAdapter

    override fun layoutId(): Int = R.layout.activity_choose_city

    override fun initViews() {
        mClose = findViewById(R.id.close)
    }

    override fun initListeners() {
        mClose?.setOnClickListener {
            finish()
        }
    }

    override fun observeChanges() {
        mViewModel.getCities().observe(this, Observer { cities ->
            mChooseCityAdapter.submitList(cities)
        })
    }

    override fun removeListeners() {
        mClose?.setOnClickListener(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mChooseCityAdapter = ChooseCityListAdapter { chosenCity ->
            setDefaultCity(chosenCity)
        }
        mBinding.cities.adapter = mChooseCityAdapter
    }

    companion object {
        const val TAG = "ChooseCityA"
    }
}