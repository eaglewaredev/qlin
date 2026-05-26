package dev.eagleware.qlin

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.Settings
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
import dev.eagleware.qlin.view.settings.NewAccountScreen
import dev.eagleware.qlin.view.settings.SettingsScreen
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
    SALES(
        route = InventoryEntry,
        selectedIcon = Icons.Filled.Receipt,
        unselectedIcon = Icons.Outlined.Receipt,
        label = "Sale"
    ),
    INVENTORY(
        route = InventoryEntry,
        selectedIcon = Icons.Filled.Sell,
        unselectedIcon = Icons.Outlined.Sell,
        label = "Inventory"
    ),
    SETTINGS(
        route = SettingsEntry,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        label = "Settings"
    ),
    PROFILE(
        route = HomeEntry,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        label = "Profile"
    )
}

@Serializable
sealed interface QRoute: NavKey

@Serializable
data object HomeEntry: QRoute

@Serializable
data object InventoryEntry: QRoute

@Serializable
data object NewAccountEntry: QRoute

@Serializable
data object SettingsEntry: QRoute
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
            entry<SettingsEntry>{
                SettingsScreen(
                    modifier = modifier,
                    backStack = backStack,
                )
            }
            entry<NewAccountEntry>{
                NewAccountScreen(
                    modifier = modifier,
                    backStack = backStack,
                )
            }
        },
        transitionSpec = {
            // Slide in from right when navigating forward
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
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