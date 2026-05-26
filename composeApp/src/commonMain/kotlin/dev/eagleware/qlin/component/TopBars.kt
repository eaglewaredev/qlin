package dev.eagleware.qlin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.eagleware.qlin.SettingsEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(){
    TopAppBar(
        title = { Text("Settings") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAccountTopBar(
    backStack: NavBackStack<NavKey>,
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "Back Navigation",
                modifier = Modifier.clickable(
                    onClick = { backStack.removeLastOrNull()}
                ).padding(start = 10.dp)
            )
        },
        title = { Text("New Account") },
    )
}