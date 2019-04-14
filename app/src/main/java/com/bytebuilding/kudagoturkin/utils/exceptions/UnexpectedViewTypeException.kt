package com.bytebuilding.kudagoturkin.utils.exceptions


class UnexpectedViewTypeException : Exception() {

    override val message: String?
        get() = "Wrong ItemViewType in RecyclerView"

}