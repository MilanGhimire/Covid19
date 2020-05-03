package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.CovidViewModel

class CovidStatusFragment : Fragment(R.layout.fragment_covid_status) {

    lateinit var viewModel: CovidViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as CovidActivity).viewModel
    }
}