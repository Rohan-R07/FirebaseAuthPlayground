package com.example.firebaseauthentication.Utils

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.firebaseauthentication.AuthViewModel

@Composable
fun PhoneLogin(viewModel: AuthViewModel?) {
    val dialogState = remember { mutableStateOf(false) }

    val otpVisibility = remember { mutableStateOf(false) }

    val otpText = remember { mutableStateOf("") }

    Row {
        Text(
            text = "Want to login via",
            fontSize = 17.sp
        )
        Text(
            text = "Phone No",
            fontSize = 17.sp,
            color = Blue,
            modifier = Modifier
                .clickable {
                    dialogState.value = true
                }
        )
    }

    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },

            ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                val phoneNo = remember { mutableStateOf<String>("") }
                val context = LocalContext.current
                OutlinedTextField(

                    value = phoneNo.value,
                    onValueChange = {
                        if (it.length <= 10) {
                            phoneNo.value = it
                        } else {

                        }
                    },
                    modifier = Modifier,
                    singleLine = true,
                    maxLines = 1,
                    prefix = {
                        Text(
                            text = "+91",
                            color = White
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    placeholder = {
                        Text(
                            text = "Enter Phone No"

                        )
                    },
                    label = {
                        Text(

                            text = "Phone No"
                        )
                    }

                )
                val activity = LocalActivity.current!!
                val dispalyName = remember { mutableStateOf("RohanR") }
                OutlinedButton(
                    onClick = {
                        viewModel?.sendVerificationCode(activity, phoneNo.value,dispalyName)
                        otpVisibility.value = true
                    }

                ) {
                    Text("Generate OTP", color = Black)
                }

//                OtpTextField(
//                    otp = otpText.value.toString(),
//                    onOtpChanged = { otpText.value = it }
//                )
//
//                if (otpVisibility.value) {
//
//                    Text(phoneNo.value, color = White)
//                }

                OutlinedTextField(
                    value = otpText.value,
                    onValueChange = {otpText.value = it},

                )

                OutlinedButton(
                    onClick = {
                        viewModel?.verifyCode(otpText)
                    }
                ) { }
            }
        }
    }
}