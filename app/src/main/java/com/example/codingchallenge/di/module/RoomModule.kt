package com.example.codingchallenge.di.module

import android.app.Application
import androidx.room.Room
import com.example.codingchallenge.common.extensions.Constants
import com.example.codingchallenge.local.db.ItunesItemDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun providesItemsItunesDatabase(app: Application) =
        Room.databaseBuilder(
            app.applicationContext,
            ItunesItemDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesItunesItemDao(itunesItemDatabase: ItunesItemDatabase) =
        itunesItemDatabase.itunesDao()
}