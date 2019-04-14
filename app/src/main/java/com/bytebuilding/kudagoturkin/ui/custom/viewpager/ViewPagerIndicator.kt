package com.bytebuilding.kudagoturkin.ui.custom.viewpager


import android.content.Context
import android.database.DataSetObserver
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.utils.loge


class ViewPagerIndicator @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr),
    ViewPager.OnAdapterChangeListener,
    ViewPager.OnPageChangeListener {

    private var viewPager: ViewPager? = null
    private val indicatorSize = context.resources.getDimensionPixelSize(R.dimen.vpi_indicator_size)
    private val indicatorMargin = context.resources.getDimensionPixelSize(R.dimen.vpi_indicator_margin)
    private var dataSetObserver: DataSetObserver? = null

    init {
        gravity = Gravity.CENTER
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        this.viewPager?.addOnAdapterChangeListener(this)
        this.viewPager?.addOnPageChangeListener(this)
        unRegisterSetObserver()
        registerSetObserver()
        inflateIndicators()
    }

    override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
        unRegisterSetObserver()
        registerSetObserver()
        inflateIndicators()
    }

    override fun onPageSelected(position: Int) {
        try {
            for (index in 0..childCount) {
                val indicatorView = getChildAt(index) as IndicatorView
                indicatorView.selectIndicator = index == position
            }
        } catch (cce: ClassCastException) {
            cce.printStackTrace()
            loge(tag = TAG, msg = cce.localizedMessage, th = cce)
        }
//        children
//            .map { it as IndicatorView }
//            .forEachIndexed { index, indicator -> indicator.selectIndicator = index == position }
    }

    override fun onPageScrollStateChanged(state: Int) {
        //empty
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //empty
    }

    private fun registerSetObserver() {
        if (dataSetObserver != null) {
            return
        }

        dataSetObserver = object : DataSetObserver() {
            override fun onChanged() {
                inflateIndicators()
            }
        }

        try {
            viewPager?.adapter?.registerDataSetObserver(dataSetObserver as DataSetObserver)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    private fun unRegisterSetObserver() {
        if (dataSetObserver == null || viewPager == null || viewPager?.adapter == null) {
            return
        }

        try {
            viewPager?.adapter?.unregisterDataSetObserver(dataSetObserver as DataSetObserver)
            dataSetObserver = null
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

    }

    private fun inflateIndicators() {
        removeAllViews()
        val layoutParams = LinearLayout.LayoutParams(indicatorSize, indicatorSize)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.marginStart = indicatorMargin / 2
            layoutParams.marginEnd = indicatorMargin / 2
        } else {
            layoutParams.leftMargin = indicatorMargin / 2
            layoutParams.rightMargin = indicatorMargin / 2
        }
        val pageCount = viewPager?.adapter?.count ?: 0

        (0 until pageCount).forEach { _ ->
            val indicatorView = IndicatorView(context)
            indicatorView.layoutParams = layoutParams
            addView(indicatorView)
        }

        viewPager?.let {
            onPageSelected(it.currentItem)
        }
    }

    companion object {
        const val TAG = "ViewPagerIndicator"
    }
}