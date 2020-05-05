package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel
import com.milanghimire.covid19.util.AdapterHelper
import com.milanghimire.covid19.util.Constants
import com.milanghimire.covid19.util.SharedPreferencesUtil
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val TAG = "SettingFragment"

    lateinit var covidViewModel: CovidViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        covidViewModel = (activity as CovidActivity).covidViewModel

        setupUI()
    }

    private fun setupUI() {
        spinnerCountrySelector.adapter =
            AdapterHelper.createAdapter(
                (context as CovidActivity),
                resources.getStringArray(R.array.countries_array).toList()
            )

        spinnerCountrySelector.setSelection(
            resources.getStringArray(R.array.countries_array)
                .indexOf(SharedPreferencesUtil.readFromPreference((activity as CovidActivity), Constants.PREF_AUTH_COUNTRY, ""))
        )

        spinnerCountrySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log .d(TAG, "noNothingSelected.")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountryName = spinnerCountrySelector.selectedItem.toString()
                Log.d(TAG, "Item changed. $selectedCountryName")
                SharedPreferencesUtil.saveToPreference((activity as CovidActivity), Constants.PREF_AUTH_COUNTRY, selectedCountryName)
                covidViewModel.getCovidCountryStatus(selectedCountryName)
            }
        }
    }
}