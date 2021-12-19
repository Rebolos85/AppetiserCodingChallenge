package com.example.codingchallenge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.codingchallenge.utils.ImageDisplayer
import com.example.codingchallenge.utils.schedulers.BaseSchedulerProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Defining the fields and methods that are needed of the child activies
 * in this way we're implementing solid principle which is dependency Inversion
 * Principle
 * @param <VB> - responsible for inflating the view binding of layout files
 * */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var binding: VB
    protected val disposables: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var imageDisplayer: ImageDisplayer

    @Inject
    lateinit var scheduler: BaseSchedulerProvider


    /** Returns an [AndroidInjector].  */
    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

    }

    // this is to bind the sub activity
    abstract fun getViewBinding(): VB

}