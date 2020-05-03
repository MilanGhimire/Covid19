package com.milanghimire.covid19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.milanghimire.covid19.R
import com.milanghimire.covid19.db.CovidDatabase
import com.milanghimire.covid19.repository.CovidRepository
import kotlinx.android.synthetic.main.activity_covid.*

class CovidActivity : AppCompatActivity() {

    lateinit var viewModel: CovidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)

        val covidRepository = CovidRepository(CovidDatabase(this))
        val viewModelProviderFactory = CovidViewModelProviderFactory(covidRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(CovidViewModel::class.java)

        bottomNavigationView.setupWithNavController(covidNavHostFragment.findNavController())
    }
}
