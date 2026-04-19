package dev.eagleware.qlin.component.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

var isDark = mutableStateOf(false)
val LocalTheme = compositionLocalOf { isDark }