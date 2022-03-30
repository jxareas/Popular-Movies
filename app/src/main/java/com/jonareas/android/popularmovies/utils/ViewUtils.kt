package com.jonareas.android.popularmovies.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline infix fun<reified T : ViewBinding>
        ViewGroup.help(crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T): T =
    LayoutInflater.from(context).let { inflater.invoke(it, this, false) }