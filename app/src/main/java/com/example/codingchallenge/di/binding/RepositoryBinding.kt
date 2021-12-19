package com.example.codingchallenge.di.binding
import com.example.codingchallenge.data.features.music.ItunesItemRepoImpl
import com.example.codingchallenge.data.features.music.MusicRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryBinding {
    /**
     * This one is provided the repository interface to be use
     * by the consumer classes
     * @return ItunesRepository which this return a remote or local data to be use
     * by the use case to perform the business logic
     */
    @Binds
    @Singleton
    abstract fun providesItunesRepository(itunesItemRepoImpl: ItunesItemRepoImpl): MusicRepository
}