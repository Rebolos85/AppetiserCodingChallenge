package com.example.codingchallenge.model.responses

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codingchallenge.other.Constants

data class ItunesBaseItemData(
    var resultCount: Int,

    val results: List<ItunesDataItem>,
) {
//    constructor() : this(0, listOf())
}