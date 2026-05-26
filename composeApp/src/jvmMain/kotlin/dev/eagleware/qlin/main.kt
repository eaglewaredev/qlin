package dev.eagleware.qlin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Qlin",
    ) {
        window.minimumSize = java.awt.Dimension(600, 800)
        App()
    }
}