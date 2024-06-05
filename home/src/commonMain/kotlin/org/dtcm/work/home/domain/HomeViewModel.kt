package org.dtcm.work.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.NewsItem
import org.dtcm.work.common.data.RecommendationItem
import org.dtcm.work.common.data.TopProductItem
import org.dtcm.work.domain.FetchAllProductsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchNewsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchRecommendationsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.FetchTopProductsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.GetHomeRecommendationsUseCase
import org.dtcm.work.domain.GetHomeScreenNewsUseCase
import org.dtcm.work.domain.GetTopProductsUseCase

class HomeViewModel(
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase,
    private val fetchRecommendationsFromFirebaseAndSaveUseCase: FetchRecommendationsFromFirebaseAndSaveUseCase,
    private val fetchTopProductsFromFirebaseAndSaveUseCase: FetchTopProductsFromFirebaseAndSaveUseCase,
    private val fetchAllProductsFromFirebaseAndSaveUseCase: FetchAllProductsFromFirebaseAndSaveUseCase,
    private val getHomeNewsInfoUseCase: GetHomeScreenNewsUseCase,
    private val getHomeRecommendationsUseCase: GetHomeRecommendationsUseCase,
    private val getTopProductsUseCase: GetTopProductsUseCase,
) : ViewModel() {

    private val _newsInfo = MutableStateFlow(listOf<NewsItem>())
    val news = _newsInfo.asStateFlow()

    private val _recommendationsList = MutableStateFlow(listOf<RecommendationItem>())
    val recommendationsList = _recommendationsList.asStateFlow()

    private val _topProductsList = MutableStateFlow(listOf<TopProductItem>())
    val topProductsList = _topProductsList.asStateFlow()

    init {
        fetchAndSaveNews()
        fetchAndSaveRecommendations()
        fetchAndSaveTopProducts()
        fetchAllProducts()
    }

    fun getNews() {
        viewModelScope.launch {
            getHomeNewsInfoUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    _newsInfo.value = resource
                } else {
                    println("News is empty")
                }
            }
        }
    }

    fun getRecommendationsList() {
        viewModelScope.launch {
            getHomeRecommendationsUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    _recommendationsList.value = resource
                } else {
                    println("Recommendations is empty")
                }
            }
        }
    }

    fun getTopProductsList() {
        viewModelScope.launch {
            getTopProductsUseCase().collect { data ->
                if (data.isNotEmpty()) {
                    _topProductsList.value = data
                } else {
                    println("Top products is empty")
                }
            }
        }
    }

    private fun fetchAndSaveNews() = viewModelScope.launch {
        fetchNewsFromFirebaseAndSaveUseCase()
    }

    private fun fetchAndSaveRecommendations() = viewModelScope.launch {
        fetchRecommendationsFromFirebaseAndSaveUseCase()
    }

    private fun fetchAndSaveTopProducts() = viewModelScope.launch {
        fetchTopProductsFromFirebaseAndSaveUseCase()
    }

    private fun fetchAllProducts() = viewModelScope.launch {
        fetchAllProductsFromFirebaseAndSaveUseCase()
    }
}