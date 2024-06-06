package org.dtcm.work.common.data

import androidx.lifecycle.SavedStateHandle

fun createSavedStateHandle(parameters: Map<String, Any>): SavedStateHandle {
    val handle = SavedStateHandle()
    parameters.forEach { (key, value) ->
        handle[key] = value
    }
    return handle
}