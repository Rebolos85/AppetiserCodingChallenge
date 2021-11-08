package com.example.codingchallenge.utils

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

/**
 * This class is responsible to load the image using glide
 */
class ImageDisplayer @Inject constructor() {


    fun displayImage(albumImage: ImageView, imageUrl: String) {
        Glide.with(albumImage)
            .load(imageUrl)
            .centerCrop()
            .thumbnail(0.5f)

            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(albumImage)


    }
}