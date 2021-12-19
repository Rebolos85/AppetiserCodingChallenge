package com.example.codingchallenge.di.binding

import com.example.codingchallenge.network.features.music.ItunesRemoteSource
import com.example.codingchallenge.network.features.music.ItunesRemoteSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton
@Module
abstract class RemoteBinding {
    @Binds
    @Singleton
    abstract fun providesRemoteSource(itunesLocalImpl: ItunesRemoteSourceImpl): ItunesRemoteSource

}