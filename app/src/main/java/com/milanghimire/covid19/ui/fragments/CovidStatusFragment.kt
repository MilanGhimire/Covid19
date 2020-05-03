package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel
import com.milanghimire.covid19.util.Resource

class CovidStatusFragment : Fragment(R.layout.fragment_covid_status) {

    lateinit var viewModel: CovidViewModel

    private val TAG = "CovidStatusFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CovidActivity).viewModel

        viewModel.covidStatus.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
//                    ?.let is used for checking if response in not null
                    response.data?.let {
                        Log.d(TAG, "Data is retrived from API.")
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