package com.bytebuilding.kudagoturkin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bytebuilding.kudagoturkin.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass


abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel>(
    clazz: KClass<VM>,
    private val isRetainInstance: Boolean = false
) : Fragment(),
    CoroutineScope {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    protected lateinit var mBinding: V
    protected val mViewModel: VM by viewModelByClass(clazz)

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = isRetainInstance
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.vm, mViewModel)

        return mBinding.root
    }

    override fun onStop() {
        mJob.cancelChildren()

        super.onStop()
    }

}