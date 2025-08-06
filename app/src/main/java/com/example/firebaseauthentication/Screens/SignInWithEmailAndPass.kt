package com.example.firebaseauthentication.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes
import com.example.firebaseauthentication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInwithEmailAndPasward(
    viewModel: AuthViewModel?,
    navBackStack: NavBackStack,
    TextSwitch: Boolean,
    siginInButtonState: MutableState<Boolean>,
) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }

        val courutineScope = rememberCoroutineScope()
        val disaplyName = remember { mutableStateOf("") }

        val signInGoogleToggle = remember { mutableStateOf(false) }

        Text(
            text = "Welcome Back",
            color = Blue,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )


        Spacer(Modifier.padding(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text("Enter Email...")
            },
            label = {
                Text(
                    text = "Email"
                )
            },
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
        )
        Spacer(Modifier.padding(10.dp))
        var passward by remember { mutableStateOf("") }

        var passwardVisibility = remember { mutableStateOf<Boolean>(false) }

        OutlinedTextField(
            value = passward,
            onValueChange = { passward = it },
            placeholder = {
                Text("Enter Passward...")
            },
            label = {
                Text("Passward")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                if (passwardVisibility.value) {
                    Icon(
                        painter = painterResource(R.drawable.outline_visibility_off_24),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwardVisibility.value = false
                            }
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.outline_visibility_24),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwardVisibility.value = true
                            }
                    )
                }
            },
            visualTransformation = if (passwardVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp)

        )


        Spacer(
            modifier = Modifier

                .padding(10.dp)
        )
        Row {
            Text(
                text = "Don't have an account? ",
                fontSize = 17.sp
            )
            Text(
                text = "Create one",
                fontSize = 17.sp,
                color = Blue,
                modifier = Modifier
                    .clickable {
                        siginInButtonState.value = !siginInButtonState.value
                    }

            )
        }
        Spacer(
            modifier = Modifier

                .padding(10.dp)
        )



        Spacer(Modifier.padding(20.dp))

        OutlinedButton(
            onClick = {
                siginInButtonState.value = true
            },
            border = BorderStroke(4.dp, color = Blue),
            modifier = Modifier
                .width(200.dp)
        ) {
            Text("Sign In", fontSize = 20.sp)

        }


    }
}