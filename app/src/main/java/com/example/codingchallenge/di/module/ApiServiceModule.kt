package com.example.codingchallenge.di.module


import com.example.codingchallenge.network.api.AppleApiServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {
    @Singleton
    @Provides
    fun provideAppleApi(retrofit: Retrofit): AppleApiServices =
        retrofit.create(AppleApiServices::class.java)
}