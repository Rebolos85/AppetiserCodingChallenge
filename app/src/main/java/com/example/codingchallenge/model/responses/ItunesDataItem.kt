package com.example.codingchallenge.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.codingchallenge.other.Constants.ITUNES_TABLE_NAME

/**
 * This data class is used to extract the JSON response
 *  into pojo to be used in the front end to display the data
 */
@Entity(tableName = ITUNES_TABLE_NAME)
class ItunesDataItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val wrapperType: String? = "",
    val explicitness: String? = "",
    val kind: String? = "",
    val trackName: String? = "",
    val artistName: String? = "",
    val collectionName: String? = "",
    val censoredName: String? = "",
    val artworkUrl100: String? = "",
    val artworkUrl60: String? = "",
    val artworkUrl30: String? = "",
    val trackPrice: String? = "",
    val currency: String? = "",
    val releaseDate: String? = "",
    val collectionExplicitness: String? = "",
    val trackExplicitness: String? = "",
    val contentAdvisoryRating: String? = "",
    val primaryGenreName: String? = "",
    val longDescription: String? = "",
    val shortDescription: String? = "",
    val viewURL: String? = "",
    val previewUrl: String? = "",
    val trackTimeMillis: String? = "",
) {
}