package com.example.codingchallenge.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.codingchallenge.ViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * @param VM- responsible for instantiating our view model
 * @param B- passing the view binding to inflate layout resources for activity
 */
abstract class BaseVMActivity<VM : BaseViewModel, B : ViewBinding> : BaseActivity<B>() {
    @Inject
    lateinit var factory: ViewModelFactory<VM>

    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[getViewModelClass()]

    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }

}
