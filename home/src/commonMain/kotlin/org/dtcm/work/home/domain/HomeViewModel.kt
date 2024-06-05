package org.dtcm.work.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.NewsItem
import org.dtcm.work.domain.FetchNewsFromFirebaseAndSaveUseCase
import org.dtcm.work.domain.GetHomeScreenNewsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val fetchNewsFromFirebaseAndSaveUseCase: FetchNewsFromFirebaseAndSaveUseCase by inject()
    private val getHomeScreenNewsUseCase: GetHomeScreenNewsUseCase by inject()

    private val _newsInfo = MutableStateFlow(listOf<NewsItem>())
    val news = _newsInfo.asStateFlow()

    init {
        fetchAndSaveNews()
    }

    fun getNews() {
        viewModelScope.launch {
            getHomeScreenNewsUseCase().collect { resource ->
                if (resource.isNotEmpty()) {
                    _newsInfo.value = resource
                }
            }
        }
    }

    private fun fetchAndSaveNews() = viewModelScope.launch {
        fetchNewsFromFirebaseAndSaveUseCase()
    }
}