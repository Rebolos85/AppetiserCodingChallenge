package com.example.codingchallenge

import android.app.Activity
import android.app.Application
import com.example.codingchallenge.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

//@HiltAndroidApp
class ItunesApplication : Application(), HasAndroidInjector {


    /* This is the new update from dagger you can view this
    as your reference https://github.com/google/dagger/commit/3bd8f707cb28fd0c5f3abb4f87658566f8b52c10
    for reducing the boiler plate
     */
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

//    @Inject
//    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    /** Returns an [AndroidInjector].  */
    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
