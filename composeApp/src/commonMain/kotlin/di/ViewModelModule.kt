package di

import androidx.lifecycle.SavedStateHandle
import org.dtcm.work.booking.BookingScreenViewModel
import org.dtcm.work.common.data.viewModelDefinition
import org.dtcm.work.favorites.FavoritesViewModel
import org.dtcm.work.gallery.GalleryScreenViewModel
import org.dtcm.work.home.domain.HomeViewModel
import org.dtcm.work.home.domain.news.NewsDetailViewModel
import org.dtcm.work.home.domain.recommendations.RecommendationsDetailViewModel
import org.dtcm.work.productdetail.ProductDetailsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModelDefinition { (handle: SavedStateHandle) -> NewsDetailViewModel(handle, get()) }
    viewModelDefinition { (handle: SavedStateHandle) ->
        RecommendationsDetailViewModel(
            handle,
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition { HomeViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModelDefinition {
        GalleryScreenViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModelDefinition { FavoritesViewModel(get(), get()) }
    viewModelDefinition { (handle: SavedStateHandle) ->
        ProductDetailsViewModel(
            handle,
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    viewModelDefinition { BookingScreenViewModel(get(),get()) }
}