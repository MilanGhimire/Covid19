package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.NewsViewModel

class LatestNewsFragment : Fragment(R.layout.fragment_latest_news) {

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as CovidActivity).newsViewModel
    }
}