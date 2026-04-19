package dev.eagleware.qlin.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.eagleware.qlin.NavItem
import kotlinx.coroutines.launch

@Composable
fun QNavigationSuiteScaffoldLayout(
    navigationSuiteState: NavigationSuiteScaffoldState,
    layoutType: NavigationSuiteType,
    backStack: NavBackStack<NavKey>,
    colors: NavigationSuiteColors = NavigationSuiteDefaults.colors(),
    content: @Composable BoxScope.() -> Unit,
){
    val scope = rememberCoroutineScope()
    val wideNavigationRailState = rememberWideNavigationRailState()
    LaunchedEffect(layoutType) {
        when (layoutType) {
            NavigationSuiteType.WideNavigationRailExpanded ->
                wideNavigationRailState.expand()

            NavigationSuiteType.WideNavigationRailCollapsed ->
                wideNavigationRailState.collapse()

            else -> Unit
        }
    }

    var currentDestination by rememberSaveable {
        mutableStateOf(NavItem.HOME)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavigationSuiteScaffoldLayout(
            state = navigationSuiteState,
            navigationSuite = {
                when (layoutType) {
                    NavigationSuiteType.ShortNavigationBarCompact,
                    NavigationSuiteType.ShortNavigationBarMedium,
                    NavigationSuiteType.NavigationBar -> {
                        ShortNavigationBar(
                            modifier = Modifier,
                            containerColor = colors.shortNavigationBarContainerColor,
                            contentColor = colors.shortNavigationBarContentColor,
                            content = {
                                NavItem.entries.forEach { item ->
                                    ShortNavigationBarItem(
                                        iconPosition = if (layoutType == NavigationSuiteType.ShortNavigationBarCompact) NavigationItemIconPosition.Top else NavigationItemIconPosition.Start,
                                        icon = {
                                            Icon(
                                                imageVector = if (currentDestination == item) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = null,
                                            )
                                        },
                                        label = { Text(item.label) },
                                        selected = currentDestination == item,
                                        onClick = {
                                            currentDestination = item
                                            backStack.add(item.route)
                                        },
                                    )
                                }
                            },
                        )
                    }

                    NavigationSuiteType.WideNavigationRailCollapsed,
                    NavigationSuiteType.WideNavigationRailExpanded -> {
                        WideNavigationRail(
                            state = wideNavigationRailState,
                            modifier = Modifier,
                            header = {
                                IconButton(
                                    modifier = Modifier.padding(start = 24.dp),
                                    onClick = {
                                        scope.launch {
                                            if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded)
                                                wideNavigationRailState.collapse()
                                            else wideNavigationRailState.expand()
                                        }
                                    },
                                ) {
                                    if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) {
                                        Icon(Icons.AutoMirrored.Filled.MenuOpen, "Collapse rail")
                                    } else {
                                        Icon(Icons.Filled.Menu, "Expand rail")
                                    }
                                }
                            },
                            colors = colors.wideNavigationRailColors,
                            content = {
                                NavItem.entries.forEach { item ->
                                    WideNavigationRailItem(
                                        modifier = Modifier.padding(
                                            if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) 5.dp else 0.dp
                                        ),
                                        iconPosition = if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) {
                                            NavigationItemIconPosition.Start
                                        } else {
                                            NavigationItemIconPosition.Top
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (currentDestination == item) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = null,
                                            )
                                        },
                                        label = { Text(item.label) },
                                        selected = currentDestination == item,
                                        onClick = {
                                            currentDestination = item
                                            backStack.add(item.route)
                                        },
                                        railExpanded = wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded,
                                    )
                                }
                            },
                        )
                    }

                    NavigationSuiteType.NavigationRail -> {
                        NavigationRail(
                            modifier = Modifier,
                            header = { },
                            containerColor = colors.navigationRailContainerColor,
                            contentColor = colors.navigationRailContentColor,
                        ) {
                            Spacer(Modifier.weight(1f))
                            NavItem.entries.forEach { item ->
                                NavigationRailItem(
                                    icon = {
                                        Icon(
                                            imageVector = if (currentDestination == item) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = null,
                                        )
                                    },
                                    label = { Text(item.label) },
                                    selected = currentDestination == item,
                                    onClick = {
                                        currentDestination = item
                                        backStack.add(item.route)
                                    },
                                )
                            }
                            Spacer(Modifier.weight(1f))
                        }
                    }

                    NavigationSuiteType.NavigationDrawer -> {
                        PermanentDrawerSheet(
                            modifier = Modifier,
                            drawerContainerColor = colors.navigationDrawerContainerColor,
                            drawerContentColor = colors.navigationDrawerContentColor,
                        ) {
                            NavItem.entries.forEach { item ->
                                NavigationDrawerItem(
                                    modifier = Modifier.padding(
                                        vertical = 5.dp,
                                        horizontal = 10.dp
                                    ),
                                    icon = {
                                        Icon(
                                            imageVector = if (currentDestination == item) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = null,
                                        )
                                    },
                                    label = { Text(item.label) },
                                    selected = currentDestination == item,
                                    onClick = {
                                        currentDestination = item
                                        backStack.add(item.route)
                                    },
                                )
                            }
                        }
                    }
                }
            },
            layoutType = layoutType,
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                MainContentBox(
                    navigationSuiteState = navigationSuiteState,
                    layoutType = layoutType,
                    content = content,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}