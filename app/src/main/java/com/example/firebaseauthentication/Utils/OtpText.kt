package com.example.firebaseauthentication.Utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpTextField(
    otp: String,
    onOtpChanged: (String) -> Unit
) : String{
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        repeat(6) { index ->
            val char = otp.getOrNull(index)?.toString() ?: ""
            OutlinedTextField(
                value = char,
                onValueChange = {
                    if (it.length <= 1 && it.all { ch -> ch.isDigit() }) {
                        val newOtp = buildString {
                            append(otp.take(index))
                            append(it)
                            append(otp.drop(index + 1))
                        }.take(6)
                        onOtpChanged(newOtp)
                    }
                },
                modifier = Modifier
                    .width(50.dp)
                    .height(64.dp)
                    .focusRequester(FocusRequester()),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
    return  otp
}
