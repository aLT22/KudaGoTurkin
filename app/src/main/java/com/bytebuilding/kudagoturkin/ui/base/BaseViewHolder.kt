package com.bytebuilding.kudagoturkin.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<V : ViewDataBinding, M : Any>(
    mBinding: V
) : RecyclerView.ViewHolder(mBinding.root) {

    abstract fun bind(model: M, listener: (M) -> Unit)

}