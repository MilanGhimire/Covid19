package com.milanghimire.covid19.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.milanghimire.covid19.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            Log.d("SplashScreenActivity", "Splashscreen is been shown.")
        }, 3000)
    }
}