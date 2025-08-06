package com.example.firebaseauthentication

import android.app.ComponentCaller
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.firebaseauthentication.Navigations.Navigation
import com.example.firebaseauthentication.Navigations.Routes
import com.example.firebaseauthentication.ui.theme.FirebaseAuthenticationTheme
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class MainActivity : ComponentActivity() {

    private lateinit var callbackManager: CallbackManager

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val viewModels = viewModels<AuthViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return AuthViewModel(applicationContext) as T
                    }
                }
            }

        )

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(
            callbackManager, object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult) {

                    viewModels.value.loginWithFacebook(result.accessToken)
                    Toast.makeText(
                        applicationContext, "Sucessfull To Login MainActivity", Toast.LENGTH_LONG
                    ).show()

                }

                override fun onCancel() {
                    Toast.makeText(
                        applicationContext, "Failed TO LOGIN MainActivity", Toast.LENGTH_LONG
                    ).show()


                }

                override fun onError(error: FacebookException) {

                    Toast.makeText(
                        this@MainActivity, "Login Error: ${error.message}", Toast.LENGTH_SHORT
                    ).show()


                }
            })



        setContent {
            FirebaseAuthenticationTheme {
                val backStack = rememberNavBackStack<Routes>(Routes.SplashScreen)
                val isSucessFaceBook = viewModels.value.facebookAuthSuessfull.collectAsState()

                Navigation(backStack, viewModels.value, facebookLogin = {
                    LoginManager.getInstance().logInWithReadPermissions(
                        this, listOf("email", "public_profile")
                    )

                    if (isSucessFaceBook.value){
                        backStack.removeAll { true }
                        backStack.add(Routes.HomeScreen)
                    }

                })

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)

        callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}


