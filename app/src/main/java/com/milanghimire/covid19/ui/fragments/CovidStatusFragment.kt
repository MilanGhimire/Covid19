package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.milanghimire.covid19.R
import com.milanghimire.covid19.db.entities.CovidCountry
import com.milanghimire.covid19.db.models.CovidCountryResponse
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel
import com.milanghimire.covid19.util.Resource
import kotlinx.android.synthetic.main.fragment_covid_status.*

class CovidStatusFragment : Fragment(R.layout.fragment_covid_status) {

    lateinit var viewModel: CovidViewModel

    private val TAG = "CovidStatusFragment"

//    private lateinit var tvTests: TextView
//    private lateinit var tvAffected: TextView
//    private lateinit var tvActive: TextView
//    private lateinit var tvRecovered: TextView
//    private lateinit var tvCritical: TextView
//    private lateinit var tvDeath: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CovidActivity).viewModel

        viewModel.covidStatus.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                    response.data?.let {
                        Log.d(TAG, "Data is retrived from API. ${response.data}")
                        updateStatusData(response.data)
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

    private fun updateStatusData(status: CovidCountryResponse) {
        // initialize the UI views
        tvTests.text = status.tests.toString()
        tvAffected.text = status.todayCases.toString()
        tvActive.text = status.active.toString()
        tvRecovered.text = status.recovered.toString()
        tvCritical.text = status.critical.toString()
        tvDeath.text = status.todayDeaths.toString()

//        Glide.with(this).load(staus)
    }
}