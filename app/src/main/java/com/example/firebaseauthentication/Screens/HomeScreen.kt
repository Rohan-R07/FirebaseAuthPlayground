package com.example.firebaseauthentication.Screens

import android.transition.CircularPropagation
import android.util.Log
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
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

            LaunchedEffect(Unit) {
                onSucess.value = false
                delay(2000)
                user?.reload()?.await()
                onSucess.value = true
                email.value = viewModel.auth.currentUser?.email.toString()
                displayName.value = viewModel.auth.currentUser?.displayName.toString()
                Log.d("RohanIsAGoodBoy", "Email: ${email.value}, Name: ${displayName.value}")
                onSucess.value = true
            }

            if (onSucess.value) {
                Text(
                    "DispalyName : ${displayName.value}",
                    fontSize = 40.sp,
                    color = White
                )

                Text(
                    "Email: ${email.value}",
                    fontSize = 30.sp,
                    color = White
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
            else{
                CircularProgressIndicator()
            }

        }
    }
}

