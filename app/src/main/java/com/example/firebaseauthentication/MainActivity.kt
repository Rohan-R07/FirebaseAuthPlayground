package com.example.firebaseauthentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.firebaseauthentication.Navigations.Navigation
import com.example.firebaseauthentication.Navigations.Routes
import com.example.firebaseauthentication.ui.theme.FirebaseAuthenticationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseAuthenticationTheme {

                val viewModels = viewModels<AuthViewModel>()
                val backStack = rememberNavBackStack<Routes>(Routes.SplashScreen)

                Navigation(backStack,viewModels.value)

            }
        }
    }
}


