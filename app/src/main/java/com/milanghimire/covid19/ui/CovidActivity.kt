package com.milanghimire.covid19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.milanghimire.covid19.R
import com.milanghimire.covid19.db.CovidDatabase
import com.milanghimire.covid19.repository.CovidRepository
import com.milanghimire.covid19.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_covid.*

class CovidActivity : AppCompatActivity() {

    lateinit var covidViewModel: CovidViewModel
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)

        val covidRepository = CovidRepository(CovidDatabase(this))
        val covidViewModelProviderFactory = CovidViewModelProviderFactory(covidRepository)
        covidViewModel = ViewModelProvider(this, covidViewModelProviderFactory).get(CovidViewModel::class.java)

        val newsRepository = NewsRepository()
        val newsViewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, newsViewModelProviderFactory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(covidNavHostFragment.findNavController())
    }
}
