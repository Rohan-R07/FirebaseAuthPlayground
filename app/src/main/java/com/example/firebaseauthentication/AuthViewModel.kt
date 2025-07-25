package com.example.firebaseauthentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel() : ViewModel() {

    val auth = FirebaseAuth.getInstance()

    val isSucessCreateUserWithEmailPass = MutableStateFlow<Boolean>(false)

    val createAccUser = MutableStateFlow<FirebaseUser?>(null)

    val isSucessSiginUserWithEmailAndPass = MutableStateFlow<Boolean>(false)

    val signInUser = MutableStateFlow<FirebaseUser?>(null)


    fun createUserEmailAndPassward(email: String, passward: String, displayName: String) {
        auth.createUserWithEmailAndPassword(email, passward)
            .addOnCompleteListener { task ->

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
}