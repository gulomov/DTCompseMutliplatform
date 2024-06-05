package org.dtcm.work.home.domain.news

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.common.data.NewsItem
import org.dtcm.work.domain.GetNewsDetailUseCase
import org.koin.compose.koinInject

private const val NEWS_ID = "newsId"

class NewsDetailViewModel(
    val savedStateHandle: SavedStateHandle,
    val getNewsDetailUseCase: GetNewsDetailUseCase
) : ViewModel() {

    private val _details = MutableStateFlow(NewsItem())
    val details = _details.asStateFlow()

    init {
        viewModelScope.launch {
            getNewsDetailUseCase(checkNotNull(savedStateHandle[NEWS_ID])).collect {
                _details.value = it
            }
        }
    }
}