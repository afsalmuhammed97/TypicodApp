package com.afsal.dev.typicodapp.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import com.afsal.dev.typicodapp.R
import com.afsal.dev.typicodapp.databinding.FragmentSettingsBinding
import com.afsal.dev.typicodapp.helper.Constents.APP_THEM


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPerf = requireActivity().getSharedPreferences(APP_THEM, Context.MODE_PRIVATE)
        val editor = sharedPerf.edit()

        val isNight = sharedPerf.getBoolean("isNighMode", false)

        setThemIcon(isNight)
        if (isNight) {
            binding.switch1.isChecked = true


        }

        binding.switch1.setOnCheckedChangeListener { button, isChecked ->

            editor.putBoolean("isNighMode", isChecked)
            editor.apply()
            setThemIcon(isChecked)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }

        }


    }

    private fun setThemIcon(isNight: Boolean) {

        if (isNight)
            binding.imageView2.setImageResource(R.drawable.ic_baseline_dark_mode_24)
        else

            binding.imageView2.setImageResource(R.drawable.ic_baseline_light_mode_24)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}