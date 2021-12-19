package com.example.codingchallenge.local.features.music.dao

import androidx.room.*
import com.example.codingchallenge.common.extensions.Constants
import com.example.codingchallenge.local.features.music.model.MusicLocalEntity
import io.reactivex.Single


@Dao
interface ItunesItemDao {
    @Query("SELECT * FROM ${Constants.ITUNES_TABLE_NAME}")
    fun getAllMusicItems(): Single<List<MusicLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(musicLocalEntity: List<MusicLocalEntity>)

}