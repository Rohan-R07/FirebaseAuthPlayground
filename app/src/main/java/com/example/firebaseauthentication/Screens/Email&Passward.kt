package com.example.firebaseauthentication.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes


@Composable
fun LoginScreen(viewModel: AuthViewModel?, navBackStack: NavBackStack) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            val emailText = remember { mutableStateOf("") }
            TextField(
                value = emailText.value,
                onValueChange = { emailText.value = it },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        "Enter Email..",
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(
                Modifier

                    .padding(20.dp)
            )
            val passward = remember { mutableStateOf("") }
            TextField(
                value = passward.value,
                onValueChange = { passward.value = it },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        "Enter Passward..",
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(
                Modifier
                    .padding(20.dp)
            )
            val isSucess = viewModel?.isSucess?.collectAsState()?.value

            val context = LocalContext.current

            Button(
                onClick = {
                    viewModel?.createUserEmailAndPassward(emailText.value, passward.value)

                }
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 20.sp
                )
            }

            if (isSucess == true) {
                Toast.makeText(context, "Logined Sucessfully", Toast.LENGTH_SHORT).show()
                navBackStack.add(Routes.HomeScreen)

            } else if(isSucess == false) {
                Toast.makeText(context, "Logined Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

