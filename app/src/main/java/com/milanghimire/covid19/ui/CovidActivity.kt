package com.milanghimire.covid19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.milanghimire.covid19.R
import kotlinx.android.synthetic.main.activity_covid.*

class CovidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)

        bottomNavigationView.setupWithNavController(covidNavHostFragment.findNavController())
    }
}
