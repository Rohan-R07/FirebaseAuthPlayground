package com.example.firebaseauthentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(): ViewModel(){

    val auth = FirebaseAuth.getInstance()

    val isSucess = MutableStateFlow<Boolean>(false)

    val checkUser = MutableStateFlow<FirebaseUser?>(null)

    fun createUserEmailAndPassward(email: String,passward: String){
        auth.createUserWithEmailAndPassword(email,passward)
            .addOnCompleteListener {task ->

                if (task.isSuccessful){
                    checkUser.value = auth.currentUser

                    isSucess.value = true
                }
                else{
                    isSucess.value = false
                }

            }
    }

}