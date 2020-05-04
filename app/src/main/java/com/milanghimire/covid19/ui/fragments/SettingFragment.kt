package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.util.AdapterHelper
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        spinnerCountrySetting.adapter =
            AdapterHelper.createAdapter(
                (context as CovidActivity),
                resources.getStringArray(R.array.countries_array).toList()
            )
    }
}