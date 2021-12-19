package com.example.codingchallenge.di.builders

import com.example.codingchallenge.di.scopes.ActivityScope
import com.example.codingchallenge.features.description.ItunesSingleDescriptionActivity
import com.example.codingchallenge.features.listofmusic.ListOfMusicItunesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilders {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeItunesDetailsActivity(): ListOfMusicItunesActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): ItunesSingleDescriptionActivity
}