package com.jonareas.android.popularmovies.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.FragmentAboutBinding


class AboutFragment : DialogFragment() {

    private var _binding : FragmentAboutBinding? = null
    val binding : FragmentAboutBinding
        get() = _binding!!

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
