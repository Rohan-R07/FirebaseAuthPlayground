package com.example.firebaseauthentication.Navigations

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey{

    @Serializable
    data object SplashScreen: Routes()

    @Serializable
    data object LoginScreen: Routes()

    @Serializable
    data object HomeScreen: Routes()
    //
//        @Serializable
//        data object PhoneLoginScreen : Routes()

}