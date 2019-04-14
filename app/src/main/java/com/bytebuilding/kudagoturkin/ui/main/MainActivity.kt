package com.bytebuilding.kudagoturkin.ui.main

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.data.model.EventItem
import com.bytebuilding.kudagoturkin.databinding.ActivityMainBinding
import com.bytebuilding.kudagoturkin.ui.adapters.rv.EventListAdapter
import com.bytebuilding.kudagoturkin.ui.base.BaseActivity
import com.bytebuilding.kudagoturkin.ui.cities.ChooseCityActivity
import com.bytebuilding.kudagoturkin.utils.launchActivity
import com.bytebuilding.kudagoturkin.utils.shortToast

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(MainActivityVM::class) {

    private var mCityPickerContainer: LinearLayoutCompat? = null
    private var mCityName: AppCompatTextView? = null

    private lateinit var mEventsAdapter: EventListAdapter

    override fun layoutId(): Int = R.layout.activity_main

    override fun initViews() {
        mCityName = findViewById(R.id.cityName)
        mCityPickerContainer = findViewById(R.id.cityPickerContainer)

        mBinding.swipeRefreshLayout.setColorSchemeResources(R.color.events_list_loading_indicator_color)
    }

    override fun initListeners() {
        mCityPickerContainer?.setOnClickListener { view ->
            launchActivity<ChooseCityActivity> { }
        }

        mBinding.swipeRefreshLayout.setOnRefreshListener {
            mViewModel.getEvents()
        }
    }

    override fun observeChanges() {
        mViewModel.mDefaultCityName.observe(this, Observer { cityName ->
            if (mCityName?.text != cityName) {
                mViewModel.getEvents()
            }
        })

        mViewModel.mEventsLiveData.observe(this, Observer { events ->
            mEventsAdapter.submitList(events)
        })
    }

    override fun removeObservers() {
        mViewModel.mDefaultCityName.removeObservers(this)
        mViewModel.mEventsLiveData.removeObservers(this)
    }

    override fun removeListeners() {
        mCityPickerContainer?.setOnClickListener(null)

        mBinding.swipeRefreshLayout.setOnRefreshListener(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mEventsAdapter = EventListAdapter { eventItem ->
            when (eventItem) {
                is EventItem.Event -> shortToast(eventItem.id.toString())
                is EventItem.EventHeader -> shortToast(eventItem.id.toString())
            }
        }

        mBinding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mEventsAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        mViewModel.getDefaultCityName()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
