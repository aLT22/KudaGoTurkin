package com.bytebuilding.kudagoturkin.ui.adapters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytebuilding.kudagoturkin.R
import com.bytebuilding.kudagoturkin.data.model.EventItem
import com.bytebuilding.kudagoturkin.databinding.ItemEventBinding
import com.bytebuilding.kudagoturkin.databinding.ItemEventHeaderBinding
import com.bytebuilding.kudagoturkin.ui.base.BaseViewHolder
import com.bytebuilding.kudagoturkin.utils.exceptions.UnexpectedViewTypeException


class EventListAdapter(
    private val mOnEventClickListener: (EventItem) -> Unit
) : ListAdapter<EventItem, RecyclerView.ViewHolder>(EventItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ItemViewTypes.VIEW_TYPE_HEADER.ordinal -> {
                EventHeaderViewHolder(
                    DataBindingUtil
                        .inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_event_header,
                            parent,
                            false
                        )
                )
            }
            ItemViewTypes.VIEW_TYPE_EVENT.ordinal -> {
                EventViewHolder(
                    DataBindingUtil
                        .inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_event,
                            parent,
                            false
                        )
                )
            }
            else -> throw UnexpectedViewTypeException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EventViewHolder ->
                holder.bind(position, getItem(position) as EventItem.Event, mOnEventClickListener)
            is EventHeaderViewHolder ->
                holder.bind(position, getItem(position) as EventItem.EventHeader, mOnEventClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0 && itemCount > 0) ItemViewTypes.VIEW_TYPE_HEADER.ordinal
        else ItemViewTypes.VIEW_TYPE_EVENT.ordinal

    class EventViewHolder(
        mBinding: ItemEventBinding
    ) : BaseViewHolder<ItemEventBinding, EventItem.Event>(mBinding)

    class EventHeaderViewHolder(
        mBinding: ItemEventHeaderBinding
    ) : BaseViewHolder<ItemEventHeaderBinding, EventItem.EventHeader>(mBinding)

    companion object {
        const val TAG = "EventListAdapter"
    }
}

private class EventItemDiffCallback : DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean =
        oldItem == newItem
}

private enum class ItemViewTypes {
    VIEW_TYPE_HEADER,
    VIEW_TYPE_EVENT;
}