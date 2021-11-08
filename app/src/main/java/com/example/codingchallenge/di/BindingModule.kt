package com.example.codingchallenge.di

import com.example.codingchallenge.data.remote.repository.ItunesRepository
import com.example.codingchallenge.data.remote.repository.impl.ItunesItemRepoImpl
import com.example.codingchallenge.model.request.Empty
import com.example.codingchallenge.model.responses.ItunesBaseItemData
import com.example.codingchallenge.use_case.GetTrackNameApiUseCase
import com.example.codingchallenge.use_case.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Binding all interface in order to be use by consumer class via  @Inject constructor
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class BindingModule {
    /**
     * This one is provided the repository interface to be use
     * by the consumer classes
     * @return ItunesRepository which this return a remote or local data to be use
     * by the use case to perform the business logic
     */
    @Binds
    @Singleton
    abstract fun providesItunesRepository(itunesItemRepoImpl: ItunesItemRepoImpl): ItunesRepository

    /**
     * This method provide the use case interface as a return type to be use to inject
     * by the consumer class
     * @return UseCase- which it is responsibility to perform the business logic
     */
    @Binds
    @Singleton
    abstract fun providesUseCases(getTrackNameApiUseCase: GetTrackNameApiUseCase): UseCase<Empty, ItunesBaseItemData>
}