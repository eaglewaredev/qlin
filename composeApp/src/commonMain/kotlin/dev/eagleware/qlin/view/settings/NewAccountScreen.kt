package dev.eagleware.qlin.view.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.eagleware.qlin.HomeEntry
import dev.eagleware.qlin.component.NewAccountTopBar
import dev.eagleware.qlin.component.theme.QlinTheme
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NewAccountScreen(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey>
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailCode by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var phoneCode by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = modifier
            .padding(15.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 600.dp, height = 800.dp)
                .align  (alignment = Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.SpaceAround,
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Enter all info"
                )
                OutlinedTextField(
                    value= name,
                    label={ Text("Name")},
                    onValueChange = {
                        name = it
                    },
                    placeholder = {Text("Full Name")},
                    modifier = Modifier
//                .height(50.dp)
                        .fillMaxWidth()
//                .padding(2.dp)
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        )
                        .focusRequester(focusRequester)
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    value= username,
                    label= {Text("Username")},
                    onValueChange = {
                        username = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType=KeyboardType.Email
                    ),
                    placeholder= {Text("@Username")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        )
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    label= {Text("Email")},
                    placeholder = { Text("email@email.com") },
                    value= email,
                    onValueChange = {
                        email = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType=KeyboardType.Email
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        ),
                    trailingIcon = {
                        IconButton(
                            shape = RoundedCornerShape(5.dp),
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Sets background to primary color
                                contentColor = MaterialTheme.colorScheme.onPrimary // Sets icon color for contrast
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(end= 10.dp)
                        ){
                            Text("Send Code")
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    label= {Text("Verification Code")},
                    placeholder = { Text("Email Verification Code") },
                    value= emailCode,
                    onValueChange = {
                        emailCode = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType=KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        ),
                    trailingIcon = {
                        IconButton(
                            shape = RoundedCornerShape(5.dp),
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Sets background to primary color
                                contentColor = MaterialTheme.colorScheme.onPrimary // Sets icon color for contrast
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(end= 10.dp)
                        ){
                            Text("Verify")
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    label= {Text("Phone")},
                    placeholder = { Text("0712345678") },
                    value= phone,
                    onValueChange = {
                        phone = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType=KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        ),
                    trailingIcon = {
                        IconButton(
                            shape = RoundedCornerShape(5.dp),
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Sets background to primary color
                                contentColor = MaterialTheme.colorScheme.onPrimary // Sets icon color for contrast
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(end= 10.dp)
                        ){
                            Text("Send Code")
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                OutlinedTextField(
                    label= {Text("Verification Code")},
                    placeholder = { Text("Phone Verification Code") },
                    value= phoneCode,
                    onValueChange = {
                        phoneCode = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType=KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(0.dp, Color.Transparent)
                        ),
                    trailingIcon = {
                        IconButton(
                            shape = RoundedCornerShape(5.dp),
                            onClick = {},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // Sets background to primary color
                                contentColor = MaterialTheme.colorScheme.onPrimary // Sets icon color for contrast
                            ),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(end= 10.dp)
                        ){
                            Text("Verify")
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )
                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    Text("Save")
                }
            }
        }

    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // Requests focus when the component enters composition
    }
}

val defaultNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
//                    mutableStateListOf(QRoute)
            subclass(HomeEntry::class, HomeEntry.serializer())
        }
    }
}

@Preview
@Composable
fun NewAccountScreenPreview() {
    QlinTheme {
        Scaffold(
            topBar = { NewAccountTopBar(rememberNavBackStack(defaultNavConfig)) }
        ) {it->
            NewAccountScreen(
                modifier = Modifier.padding(it),
                backStack = rememberNavBackStack(defaultNavConfig)
            )
        }
    }
}