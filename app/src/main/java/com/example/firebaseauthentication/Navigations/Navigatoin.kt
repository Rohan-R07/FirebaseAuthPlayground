package com.example.firebaseauthentication.Navigations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Screens.HomeScreen
import com.example.firebaseauthentication.Screens.LoginScreen
import com.example.firebaseauthentication.Screens.SplashScreen

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
                LoginScreen(authViewModel,navBackStack)
            }

            entry<Routes.SplashScreen> {
                SplashScreen(viewModel = authViewModel, navBackStack = navBackStack)
            }

            entry<Routes.HomeScreen> {
                HomeScreen(viewModel = authViewModel)
            }

        }

    )

}