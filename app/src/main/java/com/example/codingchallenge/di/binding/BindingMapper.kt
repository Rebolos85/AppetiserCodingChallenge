package com.example.codingchallenge.di.binding

import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import com.example.codingchallenge.domain.model.Music
import com.example.codingchallenge.network.features.music.model.response.MusicNetworkEntity

import com.example.codingchallenge.utils.mapper.EntityMapper
import com.example.codingchallenge.utils.mapper.impl.MusicCacheImpl
import com.example.codingchallenge.utils.mapper.impl.MusicEntityImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BindingMapper {
    @Binds
    @Singleton
    abstract fun bindingCacheMapper(musicCacheImpl: MusicCacheImpl): EntityMapper<MusicLocalEntity, Music>

    @Binds
    @Singleton
    abstract fun bindingEntityMapper(musicEntityImpl: MusicEntityImpl): EntityMapper<MusicNetworkEntity, MusicLocalEntity>
}