package dev.eagleware.qlin.view.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.eagleware.qlin.NewAccountEntry
import dev.eagleware.qlin.component.ROUNDEDCORNERSHAPE
import dev.eagleware.qlin.component.theme.isDark

@Composable
fun SettingsScreen(
    modifier: Modifier,
    backStack: NavBackStack<NavKey>,
){
    var checked by remember { mutableStateOf(isDark.value) }
    Column(
        modifier = modifier
            .padding(horizontal = 15.dp),
    ) {
//        Spacer(
//            modifier = Modifier
//                .padding(vertical = 15.dp)
//        )

//        Spacer(modifier = Modifier.padding(vertical = 7.5.dp))

        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
            ,
        ) {
            Column(
                modifier = Modifier.padding(vertical = 10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable(
                            onClick = {
                                checked = !checked
//                                accountStateHolder.saveDarkTheme(checked)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row{
                        Icon(
                            Icons.Outlined.DarkMode,
                            "Theme Mode",
                            modifier = Modifier.size(35.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                        {
                            Text("Dark Mode")
                            Text(
                                "Switch between modes", fontSize = 12.sp,
//                                style = TextStyle(
//                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
//                                )
                            )
                        }
                    }
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = !checked
                            isDark.value = checked
//                            accountStateHolder.saveDarkTheme(checked)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 7.5.dp))

        // [START staff card]
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(ROUNDEDCORNERSHAPE),
        ){
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clickable(
                            onClick = {
                                backStack.add(NewAccountEntry)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        "Account",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    Text("Add Account")
                }
            }
        }
    }
}