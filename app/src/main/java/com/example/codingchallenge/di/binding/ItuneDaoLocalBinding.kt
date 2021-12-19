package com.example.codingchallenge.di.binding
import com.example.codingchallenge.local.features.music.ItunesLocalImpl
import com.example.codingchallenge.local.features.music.ItunesLocalSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ItuneDaoLocalBinding {

    @Binds
    @Singleton
    abstract fun providesLocalDataSource(itunesLocalImpl: ItunesLocalImpl): ItunesLocalSource
}