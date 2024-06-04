package org.dtcm.work.navigationcomposables

import androidx.compose.material.contentColorFor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(MaterialTheme.colorScheme.outline),
        tonalElevation = 10.dp,
    ) {
        BottomNavItem.sections.forEach { sections ->
            NavigationBarItem(
                selected = currentRoute == sections.route,
                onClick = {
                    navController.navigate(sections.route) {
                        popUpTo(navController.graph.startDestinationRoute.toString())
                        launchSingleTop = true

                    }
                },
                icon = { Icon(imageVector = sections.icon, contentDescription = null) },
                label = { Text(text = stringResource(sections.title)) }
            )
        }
    }
}