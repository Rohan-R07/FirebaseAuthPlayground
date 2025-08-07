package com.example.firebaseauthentication.Screens

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.navigation3.runtime.NavBackStack
import com.example.firebaseauthentication.AuthViewModel
import com.example.firebaseauthentication.Navigations.Routes
import com.example.firebaseauthentication.R
import com.example.firebaseauthentication.Utils.FPhoneLogin
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.delay
import kotlin.math.log
import androidx.credentials.PublicKeyCredential
import com.google.firebase.auth.FirebaseAuth


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmaiLAndPassward(
    viewModel: AuthViewModel?,
    navBackStack: NavBackStack,
    facebookLoginOnClick: @Composable () -> Unit,
    githubLogin:@Composable () -> Unit
) {


    val bottomSheetState = remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true, // Optional: prevents half-expanded state
    )

    // google Sign In implementation
    val context = LocalContext.current

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

            var signInButton = remember { mutableStateOf(true) }



            AnimatedContent(
                targetState = signInButton.value,
                modifier = Modifier
                    .fillMaxWidth(),
                content = { swtich ->

                    if (swtich) {
                        CreateAccountEANDP(
                            viewModel, navBackStack, swtich,
                            siginInButtonState = signInButton,
                            bottomSheetState,
                            sheetState
                        )
                    } else {
                        SignInwithEmailAndPasward(
                            viewModel, navBackStack, swtich,
                            siginInButtonState = signInButton,
                        )
                    }
                }
            )


            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    color = White, modifier = Modifier
                        .width(140.dp)
                )
                Text(" OR ", color = White)

                HorizontalDivider(
                    color = White, modifier = Modifier
                        .width(140.dp)
                )
            }
            val googleLogin = remember { mutableStateOf(false) }


            // google and facebook

            val isGoogleLoginSucessfull =
                viewModel?.googleSignInClient?.isSuceessFullLogin?.collectAsState()
            val toggle = remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .clickable {
                            // TODO social providers onClick
                            viewModel?.signInWithGoogle()
                            toggle.value = true

                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)

                    ) {


                        Icon(
                            painter = painterResource(R.drawable.google_logo),
                            contentDescription = null,
                            tint = Unspecified,
                            modifier = Modifier
                                .size(25.dp)
                        )

                        Spacer(Modifier.padding(5.dp))

                        Text(
                            text = "Google",
                            modifier = Modifier
                        )
                    }
                }

                if (toggle.value) {
                    if (isGoogleLoginSucessfull?.value == true) {
                        Log.d(
                            "FirebaseLogin",
                            "Sucessfully Authinticated " + isGoogleLoginSucessfull.value.toString()
                        )
                        navBackStack.removeAll { true }
                        navBackStack.add(Routes.HomeScreen)
                        toggle.value = false
                    } else if (isGoogleLoginSucessfull?.value == false) {


                        Log.d(
                            "FirebaseLogin",
                            "AuthinticatinoFailed" + isGoogleLoginSucessfull.value.toString()
                        )
                    } else {
                        Log.d("nothing happending ", isGoogleLoginSucessfull?.value.toString())

                    }

                }

                Spacer(Modifier.padding(15.dp))
                val facebookLoginToggle = remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .clickable {
                            facebookLoginToggle.value = !facebookLoginToggle.value

                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.facebook_logo),
                            contentDescription = null,
                            tint = Unspecified,
                            modifier = Modifier
                                .size(25.dp)
                        )

                        Spacer(Modifier.padding(5.dp))

                        Text(
                            text = "Facebook",
                            modifier = Modifier
                        )

                    }
                }

                if (facebookLoginToggle.value) {
                    facebookLoginOnClick.invoke()
                    facebookLoginToggle.value = false
                }

            }

            Spacer(Modifier.padding(10.dp))
            // Github and microsoft
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val githubToggle = remember {
                    mutableStateOf(false)
                }
                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .clickable {
                            // TODO social providers onClick
                            githubToggle.value = !githubToggle.value

                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.github_image),
                            contentDescription = null,
                            tint = Unspecified,
                            modifier = Modifier
                                .size(25.dp)
                        )

                        Spacer(Modifier.padding(5.dp))

                        Text(
                            text = "GitHub",
                            modifier = Modifier
                        )

                    }
                }
                if (githubToggle.value){
                    githubLogin.invoke()
                    Toast.makeText(context,"OWrking", Toast.LENGTH_LONG).show()
                }
                Spacer(Modifier.padding(15.dp))


                Card(
                    modifier = Modifier
                        .width(120.dp)
                        .clickable {
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.micorsoft),
                            contentDescription = null,
                            tint = Unspecified,
                            modifier = Modifier
                                .size(25.dp)
                        )

                        Spacer(Modifier.padding(5.dp))

                        Text(
                            text = "Microsoft",
                            modifier = Modifier
                        )

                    }
                }


            }

            Button(
                onClick = {
                    viewModel?.loginAsGuste()
                    navBackStack.removeAll { true }
                    navBackStack.add(Routes.HomeScreen)
                },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    "Continue As Guest",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountEANDP(
    viewModel: AuthViewModel?,
    navBackStack: NavBackStack,
    TextSwitch: Boolean,
    siginInButtonState: MutableState<Boolean>,
    bottomSheetState: MutableState<Boolean>,
    sheetState: SheetState
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }


        val disaplyName = remember { mutableStateOf("") }

        val courutineScope = rememberCoroutineScope()

        if (bottomSheetState.value) {
            ModalBottomSheet(
                onDismissRequest = { bottomSheetState.value = false },
                modifier = Modifier
                    .fillMaxWidth(),
                sheetState = sheetState,

                ) {
                Spacer(
                    modifier = Modifier
                        .padding(20.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    FPhoneLogin(viewModel, navBackStack)
                }
                Spacer(
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }

        Text(
            text = if (TextSwitch) "Welcome" else "Welcome Back",
            color = Blue,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )


        OutlinedTextField(
            value = disaplyName.value,
            onValueChange = { disaplyName.value = it },
            placeholder = {
                Text("Enter Name...")
            },
            label = {
                Text(
                    text = "Name"
                )
            },
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
        )
        Spacer(Modifier.padding(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text("Enter Email...")
            },
            label = {
                Text(
                    text = "Email"
                )
            },
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
        )
        Spacer(Modifier.padding(10.dp))
        var passward by remember { mutableStateOf("") }

        var passwardVisibility = remember { mutableStateOf<Boolean>(false) }

        OutlinedTextField(
            value = passward,
            onValueChange = { passward = it },
            placeholder = {
                Text("Enter Passward...")
            },
            label = {
                Text("Passward")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                if (passwardVisibility.value) {
                    Icon(
                        painter = painterResource(R.drawable.outline_visibility_off_24),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwardVisibility.value = false
                            }
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.outline_visibility_24),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwardVisibility.value = true
                            }
                    )
                }
            },
            visualTransformation = if (passwardVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp)

        )

        val rePassErrorState = remember { mutableStateOf(false) }
        Spacer(Modifier.padding(10.dp))



        Spacer(
            modifier = Modifier
                .padding(10.dp)
        )
        Row {
            Text(
                text = "Already Have an Account? ",
                fontSize = 17.sp
            )
            Text(
                text = "Sign In",
                fontSize = 17.sp,
                color = Blue,
                modifier = Modifier
                    .clickable {
                        siginInButtonState.value = !siginInButtonState.value
                    }
            )
        }

        Spacer(
            modifier = Modifier

                .padding(10.dp)
        )
        Row {
            Text(
                text = "Want to login via", fontSize = 17.sp
            )
            Text(
                text = "Phone No",
                fontSize = 17.sp,
                color = Blue,
                modifier = Modifier.clickable {
                    bottomSheetState.value = !bottomSheetState.value
                }
            )
        }
        Spacer(Modifier.padding(10.dp))

        val conedt = LocalContext.current


        Spacer(Modifier.padding(20.dp))

        OutlinedButton(
            onClick = {
                rePassErrorState.value = true
                viewModel?.createUserEmailAndPassward(
                    email,
                    passward,
                    displayName = disaplyName.value
                )

                if (viewModel?.isSucessCreateUserWithEmailPass?.value == true) {
                    navBackStack.removeAll { true }
                    navBackStack.add(Routes.HomeScreen)
                    Toast.makeText(conedt, "Done", Toast.LENGTH_SHORT).show()

                } else if (viewModel?.isSucessCreateUserWithEmailPass?.value == false) {
                    Toast.makeText(conedt, "Failed", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(conedt, "Try Again", Toast.LENGTH_LONG).show()
                }

            },
            border = BorderStroke(4.dp, color = Blue),
            modifier = Modifier
                .width(200.dp)
        ) {
            Text("Create Account", fontSize = 20.sp)

        }
    }
}

