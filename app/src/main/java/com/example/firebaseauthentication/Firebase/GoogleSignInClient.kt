package com.example.firebaseauthentication.Firebase

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleSignInClient(
    private val context: Context,

    ) {
    private val tag = "GoogleSignINClient: "
    private val auth = com.google.firebase.Firebase.auth

    private val credentialManager = androidx.credentials.CredentialManager.create(context)

    fun isSingedIn(): Boolean {
        if (auth.currentUser != null) {
            return true
        } else return false
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    suspend fun signIn(): Boolean {
        if (isSingedIn()) {
            return true
        }

        try {
            val result = buildCredentialRquest()
            return handleSignIn(result)


        } catch (e: Exception) {
            if (e is CancellationException) throw e
            println("$tag error occured ${e.message}")

            return false
        }
    }

    private suspend fun handleSignIn(result: androidx.credentials.GetCredentialResponse): Boolean {
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {

                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag + "name: ${tokenCredential.displayName}")
                println(tag + "email: ${tokenCredential.id}")
                println(tag + "Picture: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)

                // sign in with google happends here
                val authResult = auth.signInWithCredential(authCredential).await()

                return authResult.user != null

            } catch (e: GoogleIdTokenParsingException) {
                println(tag + e.message)
                return false

            }
        } else {
            println(tag + "Credentail is not GoogleIdTokenCrednetial")

            return false
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private suspend fun buildCredentialRquest(): androidx.credentials.GetCredentialResponse {
        val request = androidx.credentials.GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("706640974156-b6acjad64uj12cg154rqsup8ss1ta853.apps.googleusercontent.com")
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            context = context,
            request = request
        )

    }
}