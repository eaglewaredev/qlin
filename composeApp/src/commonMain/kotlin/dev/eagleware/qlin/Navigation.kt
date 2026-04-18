package dev.eagleware.qlin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

fun customNavigationSuiteType(
    windowSizeClass: WindowSizeClass
): NavigationSuiteType {

    val width = windowSizeClass.widthSizeClass
    val height = windowSizeClass.heightSizeClass

    return when (width) {

        // Phones
        WindowWidthSizeClass.Compact -> {
            NavigationSuiteType.ShortNavigationBarCompact
        }

        // Large phones / small tablets
        WindowWidthSizeClass.Medium -> {
            when (height) {
                WindowHeightSizeClass.Compact ->
                    // Landscape / split screen
                    NavigationSuiteType.NavigationBar

                WindowHeightSizeClass.Medium,
                WindowHeightSizeClass.Expanded ->
                    NavigationSuiteType.ShortNavigationBarMedium

                else ->
                    NavigationSuiteType.ShortNavigationBarMedium
            }
        }

        // Tablets / Desktop
        WindowWidthSizeClass.Expanded -> {
            when (height) {
                WindowHeightSizeClass.Expanded -> {
                    // Desktop / ChromeOS
                    NavigationSuiteType.NavigationDrawer
                }

                WindowHeightSizeClass.Compact -> {
                    // Landscape tablet / short window
                    NavigationSuiteType.NavigationRail
                }

                WindowHeightSizeClass.Medium -> {
                    NavigationSuiteType.WideNavigationRailCollapsed
                }

                else -> NavigationSuiteType.WideNavigationRailCollapsed
            }
        }

        else -> NavigationSuiteType.ShortNavigationBarCompact
    }
}

sealed interface Route {

    @kotlinx.serialization.Serializable
    data object HomeRoute : Route

    @kotlinx.serialization.Serializable
    data object ShortsRoute : Route

    @kotlinx.serialization.Serializable
    data object SubscriptionsRoute : Route

    @kotlinx.serialization.Serializable
    data object LibraryRoute : Route

    @Serializable
    data class VideoDetailRoute(
        val videoId: String
    ) : Route
}

enum class NavItem(
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    HOME(
        route = Route.HomeRoute,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        label = "Home"
    ),
    SHORTS(
        route = Route.ShortsRoute,
        selectedIcon = Icons.Filled.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow,
        label = "Shorts"
    ),
    SUBSCRIPTIONS(
        route = Route.SubscriptionsRoute,
        selectedIcon = Icons.Filled.Subscriptions,
        unselectedIcon = Icons.Outlined.Subscriptions,
        label = "Subscriptions"
    ),
    LIBRARY(
        route = Route.LibraryRoute,
        selectedIcon = Icons.Filled.VideoLibrary,
        unselectedIcon = Icons.Outlined.VideoLibrary,
        label = "Library"
    )
}

fun NavigationSuiteScope.systemNavBar(
    currentNavigationItem: NavItem?,
    onNavigationItemClick: (NavItem) -> Unit
) {
    NavItem.entries.forEach { navItem ->

        val selected = currentNavigationItem == navItem

        item(
            icon = {
                Icon(
                    imageVector = if (selected)
                        navItem.selectedIcon
                    else
                        navItem.unselectedIcon,
                    contentDescription = navItem.label
                )
            },
            label = { Text(navItem.label) },
            selected = selected,
            onClick = {
                onNavigationItemClick(navItem)
            }
        )
    }
}