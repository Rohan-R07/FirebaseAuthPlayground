package com.example.firebaseauthentication.Utils

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes

@Composable
fun FPhoneLogin(viewModel: AuthViewModel?, navBackStack: NavBackStack) {

    val otpVisibility = remember { mutableStateOf(false) }

    val otpText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var displayName = remember { mutableStateOf("") }

        var phoneNo = remember { mutableStateOf("") }

        var isOTPVisible = remember { mutableStateOf(false) }
        OutlinedTextField(
            value = displayName.value,
            onValueChange = { displayName.value = it },
            placeholder = {
                Text("Enter Name...")
            },
            label = {
                Text(
                    text = "Name"
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

        OutlinedTextField(
            value = phoneNo.value,
            onValueChange = { phoneNo.value = it },
            placeholder = {
                Text("Enter Phone no..")
            },
            label = {
                Text(
                    text = "Phone No"
                )
            },
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Number
            ),
            prefix = {
                Text(
                    text = "+91",
                    fontSize = 10.sp
                )
            },
            keyboardActions = KeyboardActions(
                onGo = {
                    isOTPVisible.value = true
                    // TODO Viewmodel Implementation

                }
            )

        )
        Spacer(Modifier.padding(10.dp))

        val activity = LocalActivity.current!!

        Button(
            onClick = {
                isOTPVisible.value = true
                // TODO Viewmodel Implementation
                viewModel?.sendVerificationCode(
                    activity = activity,
                    phoneNo.value,
                    displayName
                )
            }
        ) {
            Text("Generate OTP")
        }

        var otpText = remember { mutableStateOf("") }

        AnimatedVisibility(
            visible = isOTPVisible.value
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OtpTextField(
                    otp = otpText.value,
                ) { otpText.value = it }

                Button(
                    onClick = {
                        viewModel?.verifyCode(otpText)
                        navBackStack.removeAll { true }
                        navBackStack.add(Routes.HomeScreen)
                    }
                ) {
                    Text("Verify and Login")
                }

            }

        }
    }
}

