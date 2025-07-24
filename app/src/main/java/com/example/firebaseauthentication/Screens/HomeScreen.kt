package com.example.firebaseauthentication.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.firebaseauthentication.AuthViewModel


@Composable
fun HomeScreen(viewModel: AuthViewModel) {

    Text("Hello world")

    Text(viewModel.auth.currentUser?.email.toString(), fontSize = 40.sp)

}