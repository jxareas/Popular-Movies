package com.jonareas.android.popularmovies.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonareas.android.popularmovies.utils.IMAGE_PATH_PREFIX_HIGH_RES

@BindingAdapter("imageUrl")
fun loadImageView(imageView : ImageView, url : String?) =
    Glide.with(imageView.context)
        .setDefaultRequestOptions(RequestOptions())
        .load("$IMAGE_PATH_PREFIX_HIGH_RES${url}")
        .into(imageView)


