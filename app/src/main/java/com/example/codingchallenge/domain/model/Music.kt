package com.example.codingchallenge.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// For domain model
@Parcelize
data class Music(
    val id: String,
    var name: String,
    val imageURL: String,
    val price: Float,
    val genre: String,
    val description: String = "",
    val maturityText: String,
    val artistName: String,
) : Parcelable