package com.example.firebaseauthentication.Navigations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import com.example.firebaseauthentication.Screens.SplashingScreen
import androidx.navigation3.ui.NavDisplay
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Screens.HomeScreen
import com.example.firebaseauthentication.Screens.EmaiLAndPassward

@Composable
fun Navigation(navBackStack: NavBackStack, authViewModel: AuthViewModel) {

    NavDisplay(
        backStack = navBackStack,
        modifier = Modifier
            .fillMaxSize(),
        onBack = {
            navBackStack.removeLastOrNull()
        },
        entryProvider = entryProvider {

            entry<Routes.LoginScreen> {
                EmaiLAndPassward(authViewModel,navBackStack)
            }


            entry<Routes.HomeScreen> {
                HomeScreen(viewModel = authViewModel,navBackStack)
            }

//            entry<Routes.PhoneLoginScreen> {
//                PhoneLogin(viewModel = authViewModel,navBackStack)
//            }

            entry<Routes.SplashScreen> {
                SplashingScreen(viewModel = authViewModel,navBackStack= navBackStack)
            }
        }

    )

}