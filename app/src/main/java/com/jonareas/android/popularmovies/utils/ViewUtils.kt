package com.jonareas.android.popularmovies.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.palette.graphics.Palette
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jonareas.android.popularmovies.R

inline infix fun<reified T : ViewBinding>
        ViewGroup.help(crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }

val defaultRequestOptions by lazy {
    RequestOptions()
        .error(R.drawable.no_internet)
        .placeholder(R.drawable.ic_movie_placeholder)
}

fun ImageView.load(string : String) =
    Glide.with(context)
        .applyDefaultRequestOptions(defaultRequestOptions)
        .load(string)
        .into(this)

fun ImageView.loadAsBitmap(string : String, onColorReady : (dominantColor : Int) -> Unit) =
    Glide.with(context)
        .applyDefaultRequestOptions(defaultRequestOptions)
        .asBitmap()
        .load(string)
        .listener(
            object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean,
                ): Boolean = false

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    resource?.let {
                        Palette.from(resource).generate().dominantSwatch?.rgb?.let {
                            onColorReady(it)
                        }
                    }
                    return false
                }

            }
        ).into(this)


fun isColorDark(color: Int): Boolean {
    val darkness = 1 - (0.299 * Color.red(color) +
        0.587 * Color.green(color) +
        0.114 * Color.blue(color)) / 255
    return darkness >= 0.5
}
