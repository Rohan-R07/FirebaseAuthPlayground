package com.example.firebaseauthentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(): ViewModel(){

    val auth = FirebaseAuth.getInstance()

    val isSucess = MutableStateFlow<Boolean>(false)

    val checkUser = MutableStateFlow<FirebaseUser?>(null)

    fun createUserEmailAndPassward(email: String,passward: String,displayName: String){
        auth.createUserWithEmailAndPassword(email,passward)
            .addOnCompleteListener {task ->

                if (task.isSuccessful){
                    checkUser.value = auth.currentUser
//                    val user = auth.currentUser

                    isSucess.value = true
                    val profileUpdates = userProfileChangeRequest {
                        this.displayName = displayName
                    }

                    checkUser.value?.updateProfile(profileUpdates)

                }
                else{
                    isSucess.value = false
                }
            }
    }

}