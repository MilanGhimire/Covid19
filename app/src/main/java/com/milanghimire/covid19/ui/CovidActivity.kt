package com.milanghimire.covid19.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.milanghimire.covid19.R
import com.milanghimire.covid19.db.CovidDatabase
import com.milanghimire.covid19.repository.CovidRepository
import com.milanghimire.covid19.repository.NewsRepository
import com.milanghimire.covid19.util.Constants
import com.milanghimire.covid19.util.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_covid.*

class CovidActivity : AppCompatActivity() {

    private val TAG = "CovidActivity"

    lateinit var covidViewModel: CovidViewModel
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)

        // check if the country is selected or not
        val selectedCountry = SharedPreferencesUtil.readFromPreference(this, Constants.PREF_AUTH_COUNTRY, "")
        Log.d("TAG", "Selected country is $selectedCountry")
        if (selectedCountry == "") {
            startActivity(Intent(this, WelcomeScreenActivity::class.java))
            finish()
        }

        val covidRepository = CovidRepository(CovidDatabase(this))
        val covidViewModelProviderFactory = CovidViewModelProviderFactory(covidRepository)
        covidViewModel = ViewModelProvider(this, covidViewModelProviderFactory).get(CovidViewModel::class.java)
        // covidWorldStatus is called in viewModel Initialization
        covidViewModel.getCovidCountryStatus(selectedCountry)

        val newsRepository = NewsRepository()
        val newsViewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(covidNavHostFragment.findNavController())
    }
}
