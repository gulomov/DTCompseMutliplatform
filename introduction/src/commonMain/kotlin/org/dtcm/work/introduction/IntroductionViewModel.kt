package org.dtcm.work.introduction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.dtcm.work.repository.IntroductionRepository
import org.dtcmp.work.datastore.introductionKey

class IntroductionViewModel(
    private val repository: IntroductionRepository
) : ViewModel() {

    fun setIntroductionShown() = viewModelScope.launch {
        repository.saveIntroductionData(introductionKey, true)
    }
}