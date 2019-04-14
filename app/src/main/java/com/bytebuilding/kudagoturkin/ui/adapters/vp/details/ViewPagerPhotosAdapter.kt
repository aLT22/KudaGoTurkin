package com.bytebuilding.kudagoturkin.ui.adapters.vp.details

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bytebuilding.kudagoturkin.R
import java.util.*


class ViewPagerPhotosAdapter : PagerAdapter() {

    private val mPhotos = LinkedList<String>()

    fun setPhotos(photos: Iterable<String>) {
        mPhotos.clear()
        mPhotos.addAll(photos)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = buildPageContentView(context = container.context, photoUrl = mPhotos[position])
        container.addView(view)
        return view
    }

    override fun getCount() = mPhotos.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        (container as ViewPager).removeView(`object` as View)

    override fun isViewFromObject(view: View, `object`: Any) =
        view == `object`

    private fun buildPageContentView(context: Context, photoUrl: String) =
        AppCompatImageView(context)
            .apply {
                Glide
                    .with(context)
                    .load(photoUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_camera)
                    .into(this)
            }

}