package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
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

        setupUI()

        viewModel.covidStatus.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                    response.data?.let {
                        Log.d(TAG, "Data is retrived from API. ${response.data}")
                        updateTodayStatusData(response.data)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.d(TAG, "Error occured. $message")
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "Request is Loading.")
                }
            }
        })
    }

    private fun setupUI() {

        btnToday.setOnClickListener {
            Log.d(TAG, "Btn today")
            viewModel.covidStatus.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                        response.data?.let {
                            Log.d(TAG, "Data is retrived from API. ${response.data}")
                            updateTodayStatusData(response.data)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.d(TAG, "Error occured. $message")
                        }
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "Request is Loading.")
                    }
                }
            })
        }
    }

    private fun updateTodayStatusData(status: CovidCountryResponse) {
        // initialize the UI views
        tvSelectedCountryName.text = status.country

        tvAffected.text = status.todayCases.toString()
        tvDeath.text = status.todayDeaths.toString()
        tvTests.text = status.tests.toString()
        tvActive.text = status.active.toString()
        tvRecovered.text = status.recovered.toString()
        tvCritical.text = status.critical.toString()

        Glide.with(this).load(status.countryInfo.flag).into(ivSelectedCountryFlag)
    }
}