package org.dtcm.work.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.dtcm.work.repository.IntroductionRepository
import org.dtcmp.work.datastore.introductionKey

class SplashScreenViewModel(
    private val introductionRepository: IntroductionRepository,
) : ViewModel() {
    private val _wasIntroductionShown = MutableStateFlow(false)
    val wasIntroductionShown = _wasIntroductionShown.asStateFlow()

    init {
        viewModelScope.launch {
            _wasIntroductionShown.value =
                introductionRepository.wasIntroductionShown(introductionKey)
        }
    }
}
