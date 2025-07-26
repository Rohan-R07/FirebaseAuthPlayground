package com.example.firebaseauthentication

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel() : ViewModel() {

    val auth = FirebaseAuth.getInstance()

    val isSucessCreateUserWithEmailPass = MutableStateFlow<Boolean?>(true)

    val createAccUser = MutableStateFlow<FirebaseUser?>(null)

    val isSucessSiginUserWithEmailAndPass = MutableStateFlow<Boolean>(false)

    val signInUser = MutableStateFlow<FirebaseUser?>(null)

    val anonomousLogin = MutableStateFlow<Boolean>(false)


    fun createUserEmailAndPassward(email: String, passward: String, displayName: String) {
        auth.createUserWithEmailAndPassword(email, passward)
            .addOnCompleteListener { task ->
                Log.d("FireBasing",isSucessCreateUserWithEmailPass.value.toString())
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
                Log.d("FirebasingError","Error occured while loggin in")
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

    fun loginAsGuste(){
        anonomousLogin.value = false

        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    anonomousLogin.value = true
                }else{
                    anonomousLogin.value = false
                }

            }
    }
}