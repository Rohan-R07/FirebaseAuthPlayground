package com.example.firebaseauthentication

import android.app.ComponentCaller
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalGraphicsContext
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
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private lateinit var callbackManager: CallbackManager
    private val deepLinkUri = mutableStateOf<Uri?>(null)

    private var currentIntent: Intent? = null


    val token = intent?.data?.getQueryParameter("token")
    val viewModels = viewModels<AuthViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AuthViewModel(applicationContext) as T
                }
            }
        }
    )

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentIntent = intent

        enableEdgeToEdge()

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

            val scope = rememberCoroutineScope()

            val githubLoginState = remember { mutableStateOf("") }
            FirebaseAuthenticationTheme {
                val backStack = rememberNavBackStack<Routes>(Routes.SplashScreen)
                val isSucessFaceBook = viewModels.value.facebookAuthSuessfull.collectAsState()

                val auth = FirebaseAuth.getInstance()

                Navigation(
                    backStack, viewModels.value, facebookLogin = {
                        LoginManager.getInstance().logInWithReadPermissions(
                            this, listOf("email", "public_profile")
                        )

                        if (isSucessFaceBook.value) {
                            backStack.removeAll { true }
                            backStack.add(Routes.HomeScreen)
                        }

                    },
                    githubLogin = {


//                        val currentUser = FirebaseAuth.getInstance().currentUser
//                        if (currentUser == null) {
                        viewModels.value.startGitHubLogin(this@MainActivity)

                        val isSucessGitHubLogin =
                            viewModels.value.isSucessfullGitHubLogin.collectAsState()
                        Log.d("Github",isSucessGitHubLogin.value.toString())

                        if (isSucessGitHubLogin.value) {
                            Toast.makeText(applicationContext,"Not Working Bro", Toast.LENGTH_SHORT).show()

                        }
                        else{
                            Log.d("Github",isSucessGitHubLogin.value.toString())

                            backStack.removeAll { true }
                            backStack.add(Routes.HomeScreen)
                        }
                        Log.d("Github",isSucessGitHubLogin.value.toString())

                    }
                )

            }


        }
    }

}


