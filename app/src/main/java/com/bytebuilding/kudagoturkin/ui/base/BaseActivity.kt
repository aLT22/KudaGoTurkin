package com.bytebuilding.kudagoturkin.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bytebuilding.kudagoturkin.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass


abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel>(
    clazz: KClass<VM>
) : AppCompatActivity(),
    CoroutineScope {

    protected val mJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    protected lateinit var mBinding: V
    protected val mViewModel: VM by viewModelByClass(clazz)

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun initViews()
    abstract fun initListeners()
    abstract fun removeListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.vm, mViewModel)

        initViews()
    }

    override fun onStart() {
        super.onStart()

        initListeners()
    }

    override fun onStop() {
        mJob.cancelChildren()
        removeListeners()

        super.onStop()
    }

    companion object {
        const val TAG = "BaseActivity"
    }

}