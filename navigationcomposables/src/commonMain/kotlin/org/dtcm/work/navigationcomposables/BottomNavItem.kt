package org.dtcm.work.navigationcomposables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import org.diploma.navigationcomposables.Res
import org.diploma.navigationcomposables.bottom_nav_favorite
import org.diploma.navigationcomposables.bottom_nav_gallery
import org.diploma.navigationcomposables.bottom_nav_home
import org.dtcm.work.navigationroute.ScreenRoute
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: StringResource,
) {
    object Home : BottomNavItem(
        route = ScreenRoute.HOME,
        icon = Icons.Default.Home,
        title = Res.string.bottom_nav_home
    )

    object Favorite : BottomNavItem(
        route = ScreenRoute.FAVORITE,
        icon = Icons.Default.Favorite, // TODO: Will be changed favorite
        title = Res.string.bottom_nav_favorite
    )

    object Gallery : BottomNavItem(
        route = ScreenRoute.GALLERY,
        icon = Icons.Default.ShoppingCart,
        title = Res.string.bottom_nav_gallery,
    )

    companion object {
        val sections = listOf(Home, Favorite, Gallery)
    }
}