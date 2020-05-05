package com.milanghimire.covid19.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.milanghimire.covid19.R
import com.milanghimire.covid19.ui.CovidActivity
import com.milanghimire.covid19.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detail_news.*

class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {

    lateinit var newsViewModel: NewsViewModel
    val args: DetailNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as CovidActivity).newsViewModel
        val article = args.article
        webViewArticle.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}