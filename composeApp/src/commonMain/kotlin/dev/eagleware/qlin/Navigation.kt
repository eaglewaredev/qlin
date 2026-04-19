package dev.eagleware.qlin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.eagleware.qlin.view.DashboardScreen
import dev.eagleware.qlin.view.InventoryScreen
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
    data object Home : Route

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
    val route: NavKey,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    HOME(
        route = HomeEntry,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        label = "Home"
    ),
    INVENTORY(
        route = InventoryEntry,
        selectedIcon = Icons.Filled.Sell,
        unselectedIcon = Icons.Outlined.Sell,
        label = "Price"
    ),
    SUBSCRIPTIONS(
        route = HomeEntry,
        selectedIcon = Icons.Filled.Subscriptions,
        unselectedIcon = Icons.Outlined.Subscriptions,
        label = "Settings"
    ),
    LIBRARY(
        route = HomeEntry,
        selectedIcon = Icons.Filled.VideoLibrary,
        unselectedIcon = Icons.Outlined.VideoLibrary,
        label = "Profile"
    )
}

@Serializable
sealed interface QRoute: NavKey

@Serializable
data object HomeEntry: QRoute

@Serializable
data object InventoryEntry: QRoute
@Composable
fun Navigations(
    backStack: NavBackStack<NavKey>,
    modifier: Modifier,
){
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeEntry> {
                DashboardScreen(
                    modifier = modifier
                )
            }
            entry<InventoryEntry> {
                InventoryScreen(
                    modifier = modifier,
                )
            }
        }
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