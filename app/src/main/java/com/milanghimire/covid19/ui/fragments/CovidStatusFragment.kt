package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.milanghimire.covid19.R
import com.milanghimire.covid19.models.CovidCountryResponse
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel
import com.milanghimire.covid19.util.Resource
import kotlinx.android.synthetic.main.fragment_covid_status.*

class CovidStatusFragment : Fragment(R.layout.fragment_covid_status) {

    lateinit var viewModel: CovidViewModel

    private val TAG = "CovidStatusFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CovidActivity).covidViewModel

        viewModel.covidStatus.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                    response.data?.let {
                        Log.d(TAG, "Data is retrieved from API. ${response.data}")
                        updateTodayStatusData(response.data)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.d(TAG, "Error occurred. $message")
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "Request is Loading.")
                }
            }
        })
    }

    private fun updateTodayStatusData(status: CovidCountryResponse) {
        // initialize the UI views
        tvSelectedCountryName.text = status.country

        tvTodayCase.text = String.format("%,d", status.todayCases)
        tvTodayDeaths.text = String.format("%,d", status.todayDeaths)
        tvCases.text = String.format("%,d", status.cases)
        tvDeath.text = String.format("%,d", status.deaths)
        tvTests.text = String.format("%,d", status.tests)
        tvActive.text = String.format("%,d", status.active)
        tvRecovered.text = String.format("%,d", status.recovered)
        tvCritical.text = String.format("%,d", status.critical)

        Glide.with(this).load(status.countryInfo.flag).into(ivSelectedCountryFlag)
    }
}