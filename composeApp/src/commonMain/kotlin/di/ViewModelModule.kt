package di

import androidx.lifecycle.SavedStateHandle
import org.dtcm.work.common.data.viewModelDefinition
import org.dtcm.work.gallery.GalleryScreenViewModel
import org.dtcm.work.home.domain.HomeViewModel
import org.dtcm.work.home.domain.news.NewsDetailViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModelDefinition { (handle: SavedStateHandle) -> NewsDetailViewModel(handle, get()) }
    viewModelDefinition { HomeViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModelDefinition { GalleryScreenViewModel(get(), get(), get(), get(), get(), get(), get(),get()) }
}