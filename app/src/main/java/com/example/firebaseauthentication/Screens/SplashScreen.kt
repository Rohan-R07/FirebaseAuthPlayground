package com.example.firebaseauthentication.Screens

import android.widget.Toast
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(viewModel: AuthViewModel, navBackStack: NavBackStack) {

    Column(
        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val anim = remember { mutableStateOf<Boolean>(false) }
        val checkUser = remember { mutableStateOf<Boolean?>(null) }
        LaunchedEffect(Unit) {
            anim.value = true
            delay(1000)
            if (viewModel.auth.currentUser != null) {
                checkUser.value = true
            } else if (viewModel.auth.currentUser == null) {
                checkUser.value = false

            }
        }

        val ultimateAnimState = updateTransition(
            targetState = anim.value
        )
        val animted = ultimateAnimState.animateDp(
            transitionSpec = {
                tween(
                    durationMillis = 300,
                    easing = EaseInOutBounce
                )
            },
            targetValueByState = {
                if (it) 55.dp else 0.dp
            }
        )

        Text(
            text = "Splash :) ",
            color = Blue,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .height(animted.value)

        )

        if (checkUser.value == true) {
            navBackStack.removeAll{true}
            navBackStack.add(Routes.HomeScreen)
        } else if (checkUser.value == false) {
            navBackStack.removeAll{true}
            navBackStack.add(Routes.LoginScreen)
        }

    }
}


