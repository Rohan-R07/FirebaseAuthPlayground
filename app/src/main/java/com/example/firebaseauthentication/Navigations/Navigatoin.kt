package com.example.firebaseauthentication.Navigations

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun Navigation(
    navBackStack: NavBackStack,
    authViewModel: AuthViewModel,
    facebookLogin: @Composable () -> Unit,
    githubLogin:@Composable () -> Unit
) {

    NavDisplay(
        backStack = navBackStack,
        modifier = Modifier
            .fillMaxSize(),
        onBack = {
            navBackStack.removeLastOrNull()
        },
        entryProvider = entryProvider {

            entry<Routes.LoginScreen> {
                EmaiLAndPassward(
                    authViewModel, navBackStack, facebookLoginOnClick = facebookLogin,
                    githubLogin = githubLogin
                )
            }


            entry<Routes.HomeScreen> {
                HomeScreen(viewModel = authViewModel,navBackStack)
            }


            entry<Routes.SplashScreen> {
                SplashingScreen(viewModel = authViewModel,navBackStack= navBackStack)
            }
        }

    )

}