package com.jonareas.android.popularmovies.view.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private var _binding : FragmentAboutBinding? = null
    private val binding : FragmentAboutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



}