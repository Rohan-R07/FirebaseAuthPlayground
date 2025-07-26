package com.example.firebaseauthentication.Screens

import android.R
import android.transition.CircularPropagation
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await


@Composable
fun HomeScreen(viewModel: AuthViewModel, navBackStack: NavBackStack) {

    val email = remember { mutableStateOf("") }
    val displayName = remember { mutableStateOf("") }

    val user = FirebaseAuth.getInstance().currentUser


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val onSucess = remember { mutableStateOf(false) }

            val isAnonomou = MutableStateFlow<Boolean>(false)

            LaunchedEffect(Unit) {
                onSucess.value = false
                delay(2300)
                user?.reload()?.await()
                onSucess.value = true
                email.value = viewModel.auth.currentUser?.email.toString()
                displayName.value = viewModel.auth.currentUser?.displayName.toString()
                Log.d("Rohan Is A Good Boy", "Email: ${email.value}, Name: ${displayName.value}")
                onSucess.value = true
                if (email.value == "null" || email.value == null && displayName.value == "null" || displayName.value == null) {
                    isAnonomou.value = false
                } else {
                    isAnonomou.value = true
                }
            }
            Log.d("Anonomous", isAnonomou.value.toString())
//            if (isAnonomou.collectAsState().value == false) {
            LaunchedEffect(Unit) {
                delay(2000)
            }

            if (onSucess.value) {
                if (email.value.toString() != "null" && displayName.value.toString() != "null") {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Hellow Welcome : ${displayName.value}",
                            fontSize = 30.sp,
                            color = White,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            "Email: ${email.value}",
                            fontSize = 20.sp,
                            color = White,
                            textAlign = TextAlign.Center
                        )

                        Button(
                            onClick = {
                                viewModel.auth.signOut()
                                navBackStack.removeAll { true }
                                navBackStack.add(Routes.LoginScreen)
                            }
                        ) {
                            Text("Sign Out", fontSize = 12.sp)
                        }
                    }
                } else {
                    Text(
                        text = "You are a Guest",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = White,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Button(
                        onClick = {
                            viewModel.auth.signOut()
                            navBackStack.removeAll { true }
                            navBackStack.add(Routes.LoginScreen)
                        }
                    ) {
                        Text("Sign IN", fontSize = 12.sp)
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}
