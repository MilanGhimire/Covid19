package com.milanghimire.covid19.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milanghimire.covid19.repository.CovidRepository

@Suppress("UNCHECKED_CAST")
class CovidViewModelProviderFactory(
    val app: Application,
    val covidRepository: CovidRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CovidViewModel(app, covidRepository) as T
    }
}