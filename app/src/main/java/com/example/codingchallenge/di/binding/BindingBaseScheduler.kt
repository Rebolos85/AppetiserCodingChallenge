package com.example.codingchallenge.di.binding

import com.example.codingchallenge.utils.schedulers.BaseSchedulerProvider
import com.example.codingchallenge.utils.schedulers.impl.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BindingBaseScheduler {
    @Singleton
    @Binds
    abstract fun bindsBaseScheduler(schedulerProviderImpl: SchedulerProviderImpl): BaseSchedulerProvider
}