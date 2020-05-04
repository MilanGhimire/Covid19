package com.milanghimire.covid19.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milanghimire.covid19.repository.CovidRepository

@Suppress("UNCHECKED_CAST")
class CovidViewModelProviderFactory(
    val covidRepository: CovidRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CovidViewModel(covidRepository) as T
    }
}