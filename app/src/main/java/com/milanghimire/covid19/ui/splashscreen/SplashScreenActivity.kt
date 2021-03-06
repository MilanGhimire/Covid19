package com.milanghimire.covid19.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // for creating full screen
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            Log.d("SplashScreenActivity", "Splashscreen is been shown.")
            startActivity(Intent(this, CovidActivity::class.java))
            finish()
        }, 3000)
    }
}