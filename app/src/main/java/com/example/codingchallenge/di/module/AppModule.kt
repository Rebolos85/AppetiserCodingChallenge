package com.example.codingchallenge.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * This class is used to provide all depedency needed to be consumed by consumer class
 * via @Inject
 *
 */

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun providesApplicationContext(app: Application): Context

}