package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
                        Toast.makeText(activity as CovidActivity, message, Toast.LENGTH_SHORT).show()
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
        tvTodayCase.text = String.format("%,d", status.todayCases)
        tvTodayDeaths.text = String.format("%,d", status.todayDeaths)
        tvCases.text = String.format("%,d", status.cases)
        tvDeath.text = String.format("%,d", status.deaths)
        tvTests.text = String.format("%,d", status.tests)
        tvActive.text = String.format("%,d", status.active)
        tvRecovered.text = String.format("%,d", status.recovered)
        tvCritical.text = String.format("%,d", status.critical)
    }
}