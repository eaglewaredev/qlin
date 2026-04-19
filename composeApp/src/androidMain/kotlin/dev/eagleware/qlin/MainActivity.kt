package dev.eagleware.qlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import dev.eagleware.qlin.component.theme.isDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {

            val barColor = MaterialTheme.colorScheme.background.toArgb()

            LaunchedEffect(isDark.value) {
                if (!isDark.value) {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(
                            barColor, barColor,
                        ),
                        navigationBarStyle = SystemBarStyle.light(
                            barColor, barColor,
                        ),
                    )
                } else {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.dark(
                            barColor,
                        ),
                        navigationBarStyle = SystemBarStyle.dark(
                            barColor,
                        ),
                    )
                }
            }

            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}