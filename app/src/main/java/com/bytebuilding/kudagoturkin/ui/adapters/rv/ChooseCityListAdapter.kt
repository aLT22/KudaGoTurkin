package com.bytebuilding.kudagoturkin.ui.adapters.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.data.model.City
import com.bytebuilding.kudagoturkin.databinding.ItemCityBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseViewHolder
import com.bytebuilding.kudagoturkin.utils.getDefaultCity


class ChooseCityListAdapter(
    private val mOnCityClickListener: (City) -> Unit
) : ListAdapter<City, ChooseCityListAdapter.CityViewHolder>(CityItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(
            DataBindingUtil
                .inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_city,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position), mOnCityClickListener)
    }

    class CityViewHolder(
        private val mBinding: ItemCityBinding
    ) : BaseViewHolder<ItemCityBinding, City>(mBinding) {

        override fun bind(model: City, listener: (City) -> Unit) {
            mBinding.root.setOnClickListener {
                listener.invoke(model)
            }

            mBinding.cityName.text = model.name
            mBinding.isCityChosen.visibility =
                if (mBinding.root.context.getDefaultCity() == model) View.VISIBLE
                else View.GONE
        }
    }
}

private class CityItemDiffCallback : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
        oldItem == newItem

    companion object {
        const val TAG = "CityItemDiffCallback"
    }

}