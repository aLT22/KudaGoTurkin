package com.bytebuilding.kudagoturkin.ui.adapters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.data.model.City
import com.bytebuilding.kudagoturkin.databinding.ItemCityBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseViewHolder
import com.bytebuilding.kudagoturkin.utils.getDefaultCityName


class ChooseCityListAdapter(
    private val mOnCityClickListener: (city: City, previousCityIndex: Int, newCityIndex: Int) -> Unit
) : ListAdapter<City, ChooseCityListAdapter.CityViewHolder>(CityItemDiffCallback()) {

    var previousCityIndex: Int = -1

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
        if (getItem(position).name == holder.itemView.context.getDefaultCityName()) {
            previousCityIndex = position
        }
        holder.bind(position, getItem(position), null)
    }

    inner class CityViewHolder(
        private val mBinding: ItemCityBinding
    ) : BaseViewHolder<ItemCityBinding, City>(mBinding) {
        override fun bind(position: Int, model: City, listener: ((City) -> Unit)?) {
            super.bind(position, model, listener)

            mBinding.root.setOnClickListener {
                mOnCityClickListener.invoke(model, previousCityIndex, position)
                previousCityIndex = position
            }
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