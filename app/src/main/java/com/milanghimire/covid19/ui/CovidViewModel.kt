package com.milanghimire.covid19.ui

import androidx.lifecycle.ViewModel
import com.milanghimire.covid19.repository.CovidRepository

class CovidViewModel(
    val covidRepository: CovidRepository
) : ViewModel() {
}