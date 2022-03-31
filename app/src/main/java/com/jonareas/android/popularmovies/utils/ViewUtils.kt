package com.jonareas.android.popularmovies.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline infix fun<reified T : ViewBinding>
        ViewGroup.help(crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }

fun isColorDark(color: Int): Boolean {
    val darkness = 1 - (0.299 * Color.red(color) +
        0.587 * Color.green(color) +
        0.114 * Color.blue(color)) / 255
    return darkness >= 0.5
}
