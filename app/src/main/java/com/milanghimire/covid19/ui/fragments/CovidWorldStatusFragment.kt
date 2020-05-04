package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.milanghimire.covid19.R
import com.milanghimire.covid19.models.CovidWorldStatusResponse
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel
import com.milanghimire.covid19.util.Resource
import kotlinx.android.synthetic.main.fragment_covid_world_status.*

class CovidWorldStatusFragment : Fragment(R.layout.fragment_covid_world_status) {

    lateinit var viewModel: CovidViewModel

    private val TAG = "CovidWorldStatusFgmt"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CovidActivity).covidViewModel

        viewModel.covidWorldStatus.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                    response.data?.let {
                        Log.d(TAG, "Data is retrived from API. ${response.data}")
                        updateTodayWorldStatusData(response.data)
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

    private fun updateTodayWorldStatusData(status: CovidWorldStatusResponse) {
        // initialize the UI views
        tvTodayCase.text = status.todayCases.toString()
        tvTodayDeaths.text = status.todayDeaths.toString()
        tvCases.text = status.cases.toString()
        tvDeath.text = status.deaths.toString()
        tvTests.text = status.tests.toString()
        tvActive.text = status.active.toString()
        tvRecovered.text = status.recovered.toString()
        tvCritical.text = status.critical.toString()
    }
}