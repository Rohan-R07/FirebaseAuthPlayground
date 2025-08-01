package com.example.firebaseauthentication.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.coroutines.ContinuationInterceptor.Key

@Composable
fun OtpTextField(
    otp: String,
    onOtpChanged: (String) -> Unit
) {
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val otpChars =
        remember { mutableStateListOf<Char>(*otp.padEnd(6, ' ').toCharArray().toTypedArray()) }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(3.dp)
            ) {
                repeat(6) { index ->
                    val char = otpChars[index].takeIf { it != ' ' }?.toString() ?: ""

                    OutlinedTextField(
                        value = char,
                        onValueChange = { value ->
                            if (value.length == 1 && value[0].isDigit()) {
                                otpChars[index] = value[0]
                                onOtpChanged(otpChars.joinToString(""))

                                if (index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            } else if (value.isEmpty()) {
                                otpChars[index] = ' '
                                onOtpChanged(otpChars.joinToString(""))
                            }
                        },
                        modifier = Modifier
                            .size(width = 44.dp, height = 50.dp) // Keeps all boxes uniform
                            .focusRequester(focusRequesters[index])
                            .onPreviewKeyEvent { event ->
                                if (event.key == _root_ide_package_.androidx.compose.ui.input.key.Key.Backspace && event.type == KeyEventType.KeyDown) {
                                    if (otpChars[index] != ' ') {
                                        otpChars[index] = ' '
                                        onOtpChanged(otpChars.joinToString(""))
                                        true
                                    } else if (index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                        true
                                    } else false
                                } else false
                            },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            lineHeight = 20.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(6.dp),
                        placeholder = {
                            Text(
                                " ",
                                color = Color.Transparent
                            )
                        } // Keeps height uniform
                    )

                }
            }

            LaunchedEffect(Unit) {
                if (otp.trim().isEmpty()) {
                    focusRequesters[0].requestFocus()
                }
            }
        }
    }
}

