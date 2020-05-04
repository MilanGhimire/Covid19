package com.milanghimire.covid19.ui

import androidx.lifecycle.ViewModel
import com.milanghimire.covid19.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}