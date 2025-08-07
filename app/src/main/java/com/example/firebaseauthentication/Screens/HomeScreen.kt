package com.example.firebaseauthentication.Screens

import android.R
import android.transition.CircularPropagation
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.platform.LocalContext
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

    val phoneNo = remember { mutableStateOf("") }

    val pofilePicture = remember { mutableStateOf("") }

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
                delay(2300)
                if (email.value.toString() == "null" || displayName.value.toString() == "null" || phoneNo.value.toString() == "null") {
                    user?.reload()?.await()
                } else {

                    onSucess.value = true
                    email.value = viewModel.auth.currentUser?.email.toString()
                    displayName.value = viewModel.auth.currentUser?.displayName.toString()
                    phoneNo.value = viewModel.auth.currentUser?.phoneNumber.toString()
                    pofilePicture.value = viewModel.auth.currentUser?.photoUrl.toString()
                    onSucess.value = true
                }


            }
            Log.d("Anonomous", isAnonomou.collectAsState().value.toString())


            Log.d("Roin", "____________")

            if (onSucess.value) {

                when {
                    (email.value.toString() == "null" && displayName.value == "null" && phoneNo.value.toString() != "null") -> {
                        // TODO Phone Login

                        Text(
                            text = "Logined via Phone",
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.padding(10.dp))
                        Text(
                            text = "phone:- ${phoneNo.value}",
                            fontSize = 20.sp,
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            softWrap = true,
                            maxLines = 4,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(Modifier.padding(10.dp))

                        Button(
                            onClick = {
                                viewModel.auth.signOut()
                                navBackStack.removeAll { true }
                                navBackStack.add(Routes.LoginScreen)
                            }
                        ) {
                            Text("Log Out", fontSize = 12.sp)
                        }

                        //deleting User

                        Spacer(Modifier.padding(10.dp))
                        Button(
                            onClick = {
                                viewModel.auth.signOut()
                                navBackStack.removeAll { true }
                                navBackStack.add(Routes.LoginScreen)
                            }
                        ) {
                            Text("Delete User", fontSize = 12.sp)
                        }
                    }


                    (email.value.toString() != "null" && displayName.value.toString() != "null" && phoneNo.value.toString() == "null")
                        -> {
                        // TODO Email and passward login /Sigin up

                        Text(
                            text = "Logined via Email and Passward/Social providers",
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.padding(10.dp))
                        Text(
                            text = "NAME:- ${displayName.value}",
                            fontSize = 20.sp,
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            softWrap = true,
                            maxLines = 4,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "EMAIL:- ${email.value}",
                            fontSize = 16.sp,
                            color = White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                            softWrap = true,
                            maxLines = 4,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(Modifier.padding(10.dp))
                        Button(
                            onClick = {

                                viewModel.auth.currentUser?.delete()
                                    ?.addOnSuccessListener {


                                        navBackStack.removeAll { true }
                                        navBackStack.add(Routes.LoginScreen)
                                    }
                                    ?.addOnFailureListener {
                                        Log.d(
                                            "DeleteUserError",
                                            "Error deleting user: ${it.message}"
                                        )
                                    }

                            }
                        ) {
                            Text("Sign IN", fontSize = 12.sp)
                        }

                        //deleting User

                        Spacer(Modifier.padding(10.dp))
                        Button(
                            onClick = {
                                viewModel.auth.signOut()
                                navBackStack.removeAll { true }
                                navBackStack.add(Routes.LoginScreen)
                            }
                        ) {
                            Text("Delete User", fontSize = 12.sp)
                        }
                    }

                    (email.value.toString() == "null" && displayName.value.toString() == "null" && phoneNo.value.toString() == "null")
                        -> {
                        Text(
                            text = "Welcome Guest",
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.padding(10.dp))

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
                }


            } else {
                CircularProgressIndicator()
            }

        }
    }
}
