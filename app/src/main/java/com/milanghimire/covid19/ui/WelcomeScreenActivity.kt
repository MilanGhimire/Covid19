package com.milanghimire.covid19.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.milanghimire.covid19.R
import com.milanghimire.covid19.util.AdapterHelper
import kotlinx.android.synthetic.main.activity_welcome_screen.*

class WelcomeScreenActivity : AppCompatActivity() {

    private val TAG = "WelcomeScreenActivity"

    private lateinit var btnStart: Button
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        context = this

        setupUI()
    }

    private fun setupUI() {
        Log.d(TAG, "setupUI()")

        btnStart = findViewById(R.id.btnStart)

        spinnerWelcomeSpinner.adapter =
            AdapterHelper.createAdapter(
                context,
                resources.getStringArray(R.array.countries_array).toList()
            )

        setupBtnStart()
    }

    private fun setupBtnStart() {
        btnStart.setOnClickListener {
            startActivity(Intent(context, CovidActivity::class.java))
            finish()
        }
    }
}