package org.dtcm.work.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.data.NewsItem
import org.dtcm.work.common.data.data.RecommendationItem
import org.dtcm.work.common.data.data.TopProductItem
import org.dtcm.work.common.data.navigation.ScreenRoute
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
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
    private val navigationRoute = MutableStateFlow<String?>(null)

    init {
        fetchAndSaveNews()
        fetchAndSaveRecommendations()
        fetchAndSaveTopProducts()
        fetchAllProducts()
        viewModelScope.launch {
            combine(
                getHomeNewsInfoUseCase(),
                getHomeRecommendationsUseCase(),
                getTopProductsUseCase(),
                navigationRoute
            ) { news, recommendations, products, route ->
                HomeUiState(
                    isLoading = false,
                    navigationRoute = route,
                    newsInfo = news,
                    recommendationsList = recommendations,
                    topProductsList = products
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }

    fun onNewsClicked(newsId: Int) {
        val route = ScreenRoute.NEWS_DETAILS.replace(
            "{newsId}", newsId.toString()
        )
        navigationRoute.value = route
    }

    fun onRecommendationsClicked(brand: String) {
        val route = ScreenRoute.RECOMMENDATION_DETAILS.replace(
            "{brandName}", brand
        )
        navigationRoute.value = route
    }

    fun resetOnClick() {
        navigationRoute.value = null
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