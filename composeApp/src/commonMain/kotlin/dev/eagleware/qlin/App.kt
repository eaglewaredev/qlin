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
import dev.eagleware.qlin.component.theme.LocalTheme
import dev.eagleware.qlin.component.theme.QlinTheme
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
    CompositionLocalProvider(LocalTheme provides LocalTheme.current){
        QlinTheme(darkTheme = LocalTheme.current.value) {
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

}

val mainAppScreens = listOf(
    NavItem.HOME.route,
    NavItem.INVENTORY.route,
    NavItem.SETTINGS.route,
)