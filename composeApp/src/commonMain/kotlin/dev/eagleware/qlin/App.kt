package dev.eagleware.qlin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.eagleware.qlin.component.QNavigationSuiteScaffoldLayout
import kotlinx.coroutines.launch
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.painterResource

import qlin.composeapp.generated.resources.Res
import qlin.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun App() {
    MaterialTheme {
//        // Basic parameters
//        val itemCount = listOf("Three", "Four", "Five")
//        val alwaysShowLabel = remember { mutableStateOf(false)}
//        val useDifferentIconsForStates by remember { mutableStateOf(false) }
//
//// NavigationBar parameters
//        val contentColor =MaterialTheme.colorScheme.contentColorFor(NavigationBarDefaults.containerColor)
//        val tonalElevation  = 3f
//
//// Item color parameters
//        val useCustomItemColors by remember{mutableStateOf(false)}
//        val selectedIconColor  = MaterialTheme.colorScheme.onSecondaryContainer
//        val selectedTextColor  = MaterialTheme.colorScheme.onSecondaryContainer
//        val indicatorColor  = MaterialTheme.colorScheme.secondaryContainer
//        val unselectedIconColor  =MaterialTheme.colorScheme.onSurfaceVariant
//        val unselectedTextColor  = MaterialTheme.colorScheme.onSurfaceVariant
//
//// State
//        var selectedItem by remember { mutableIntStateOf(0) }
//
//        // Snackbar state
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//        val showSnackbarHost = remember { mutableStateOf(false) }
//
//// Define items based on the selected count
//        val items = when (itemCount) {
//            listOf("Five") -> listOf("Home", "Messages", "Favorites", "Profile", "Settings")
//            listOf("Four") -> listOf("Home", "Messages", "Favorites", "Profile")
//            else -> listOf("Home", "Favorites", "Profile")
//        }
//
//// Define selected icons
//        val selectedIcons = listOf(
//            Icons.Filled.Home,
//            Icons.Filled.Email,
//            Icons.Filled.Favorite,
//            Icons.Filled.Person,
//            Icons.Filled.Settings
//        )
//
//// Define unselected icons (used only if useDifferentIconsForStates is true)
//        val unselectedIcons = listOf(
//            Icons.Outlined.Home,
//            Icons.Outlined.Email,
//            Icons.Outlined.Favorite,
//            Icons.Outlined.Person,
//            Icons.Outlined.Settings
//        )
//
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//
//            bottomBar = {
//                NavigationBar(
//                    modifier = Modifier.fillMaxWidth()
//                        .height(72.dp),
//                    contentColor = contentColor,
//                    tonalElevation = tonalElevation.dp,
//                    windowInsets = NavigationBarDefaults.windowInsets,
//
//                    ) {
//                    // Use RowScope to properly layout items
//                    items.forEachIndexed { index, item ->
//                        NavigationBarItem(
//                            selected = selectedItem == index,
//                            onClick = { selectedItem = index },
//                            icon = {
//                                Icon(
//                                    imageVector = if (useDifferentIconsForStates) {
//                                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index]
//                                    } else {
//                                        selectedIcons[index]
//                                    },
//                                    contentDescription = item
//                                )
//                            },
//                            label = { Text(item) },
//                            alwaysShowLabel = alwaysShowLabel.value,
//                            colors = if (useCustomItemColors) {
//                                NavigationBarItemDefaults.colors(
//                                    selectedIconColor = selectedIconColor,
//                                    selectedTextColor = selectedTextColor,
//                                    indicatorColor = indicatorColor,
//                                    unselectedIconColor = unselectedIconColor,
//                                    unselectedTextColor = unselectedTextColor
//                                )
//                            } else {
//                                NavigationBarItemDefaults.colors()
//                            }
//                        )
//                    }
//                }
//            },
//            snackbarHost = {
//                if (showSnackbarHost.value) {
//                    SnackbarHost(hostState = snackbarHostState)
//                }
//            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("FAB clicked")
//                        }
//                    }
//                ) {
//                    Icon(Icons.Filled.Add, contentDescription = "Add")
//                }
//            }
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(it)
//                    .fillMaxWidth()
//            ) {
//                // Content area (takes available space)
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Icon(
//                            imageVector = selectedIcons[selectedItem.coerceIn(
//                                0,
//                                selectedIcons.size - 1
//                            )],
//                            contentDescription = null,
//                            modifier = Modifier.size(64.dp),
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Text(
//                            text = "${items[selectedItem.coerceIn(0, items.size - 1)]} Screen",
//                            style = MaterialTheme.typography.headlineMedium
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "Selected item: ${selectedItem + 1} of ${items.size}",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                }
//
//                // Navigation bar at the bottom (outside the Box)
//
//            }
//        }

        val config = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
//                    mutableStateListOf(QRoute)
                    subclass(HomeEntry::class, HomeEntry.serializer())
                }
            }
        }

        val backStack = rememberNavBackStack(config, HomeEntry)
        val navigationSuiteState = rememberNavigationSuiteScaffoldState()

        val windowSizeClass = calculateWindowSizeClass()
        val customLayoutType = customNavigationSuiteType(windowSizeClass)

        LaunchedEffect(backStack.lastOrNull()) {
            if(backStack.lastOrNull() in mainAppScreens) {
                navigationSuiteState.show()
            }else{
                navigationSuiteState.hide()
            }
        }

        QNavigationSuiteScaffoldLayout(
            navigationSuiteState = navigationSuiteState,
            layoutType = customLayoutType,
            backStack = backStack,
//            onNavigationItemClick = { navItem ->
//                navController.navigate(navItem.route) {
//                    popUpTo(Route.HomeRoute) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            },
            content = {
                Navigations(
                    backStack = backStack,
                    modifier = Modifier,
                )
            }
        )
    }
}

val mainAppScreens = listOf(
    NavItem.HOME.route,
    NavItem.INVENTORY.route,
)