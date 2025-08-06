package com.example.firebaseauthentication

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentication.Firebase.GoogleSignInClient
import com.facebook.AccessToken
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class AuthViewModel(private val context: Context) : ViewModel() {

    val auth = FirebaseAuth.getInstance()

    val isSucessCreateUserWithEmailPass = MutableStateFlow<Boolean?>(true)

    val createAccUser = MutableStateFlow<FirebaseUser?>(null)

    val isSucessSiginUserWithEmailAndPass = MutableStateFlow<Boolean>(false)

    val signInUser = MutableStateFlow<FirebaseUser?>(null)

    val anonomousLogin = MutableStateFlow<Boolean>(false)


    fun createUserEmailAndPassward(email: String, passward: String, displayName: String) {
        auth.createUserWithEmailAndPassword(email, passward)
            .addOnCompleteListener { task ->
                Log.d("FireBasing", isSucessCreateUserWithEmailPass.value.toString())
                if (task.isSuccessful) {
                    createAccUser.value = auth.currentUser

                    isSucessCreateUserWithEmailPass.value = true
                    val profileUpdates = userProfileChangeRequest {
                        this.displayName = displayName
                    }

                    createAccUser.value?.updateProfile(profileUpdates)

                } else {
                    isSucessCreateUserWithEmailPass.value = false
                }
            }
            .addOnFailureListener { failed ->
                Log.d("FirebasingError", "Error occured while loggin in")
            }
    }

    fun siginInEmailAndPassward(email: String, passward: String) {
        auth.signInWithEmailAndPassword(email, passward)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    signInUser.value = auth.currentUser
                    isSucessSiginUserWithEmailAndPass.value = true
                } else {
                    isSucessSiginUserWithEmailAndPass.value = false
                }
            }
    }

    fun loginAsGuste() {
        anonomousLogin.value = false

        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    anonomousLogin.value = true
                } else {
                    anonomousLogin.value = false
                }

            }
    }

    val loginSucess = MutableStateFlow<Boolean?>(false)
    val verificationId = MutableStateFlow<String>("")
    val recivedOtp = MutableStateFlow<Boolean>(false)
    val otpRecivingError = MutableStateFlow<Boolean>(false)

    fun sendVerificationCode(
        activity: Activity,
        phoneNO: String,
        displayName: MutableState<String>
    ) {

//        val userProfile = FirebaseAuth.getInstance().currentUser
//        val profileUpdates = UserProfileChangeRequest.Builder()
//            .setDisplayName(displayName.value)
//            .build()

// 🔄 Fix: define option before using it

        val option = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNO")
            .setTimeout(110L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // First sign in the user
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("PhoneAuth", "User signed in successfully")


                            } else {
                                Log.d("PhoneAuth", "Sign-in failed: ${task.exception}")
                            }
                        }
                }

                override fun onVerificationFailed(phoneAuthError: FirebaseException) {
                    Log.d("PhoneAuth", "Verification failed: ${phoneAuthError.message}")
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    verificationId.value = p0
                    recivedOtp.value = true
                    otpRecivingError.value = true
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)


    }

    fun verifyCode(otp: MutableState<String>) {
        val credintial = PhoneAuthProvider.getCredential(
            verificationId.value, otp.value
        )
        credintialLogin(credintial)

    }


    private fun credintialLogin(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginSucess.value = task.isSuccessful
                    Log.d(
                        "FirebasePhoneAuth",
                        "Login Success: ${auth.currentUser?.email}"
                    )
                } else {
                    Log.e(
                        "FirebasePhoneAuth",
                        "Login Failed: ${task.exception?.message}"
                    )
                }
            }


    }

    // GOOGLE LOGIN CRED MANAGER

    val googleSignInClient = GoogleSignInClient(context = context)

//    val isSucesses = MutableStateFlow<Boolean>(googleSignInClient.isSucessed.value)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun signInWithGoogle() {
        viewModelScope.launch {
            googleSignInClient.signIn()
//            Log.d("GoogleSignINClient:","From ViewModel" + isSucesses.value.toString())
        }

    }

    val facebookAuthSuessfull = MutableStateFlow<Boolean>(false)

    fun loginWithFacebook(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    facebookAuthSuessfull.value = true
                    Log.d("FirebaseAuth ", "completed Login")
                } else {
                    facebookAuthSuessfull.value = false
                    Log.d("FirebaseAuth ", "Failed To facebook Login ")
                }

            }
            .addOnFailureListener { fail ->
                Log.d("FirebaseAuth ", fail.message.toString())

            }

    }

}
