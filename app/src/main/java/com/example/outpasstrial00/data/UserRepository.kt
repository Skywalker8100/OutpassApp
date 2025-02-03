package com.example.outpasstrial00.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    private lateinit var documentId: String

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    suspend fun updateComingInTime(): Result<Boolean> {
        val currentUser = firebaseAuth.currentUser
        val userEmail = currentUser?.email ?: return Result.Error(Exception("User not logged in"))
        return try {
            firestore.collection("users")
                .document(userEmail)
                .collection("outings")
                .document(documentId)
                .update("inTime", System.currentTimeMillis())
                .await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun uploadUserData(reason: String): Result<Boolean> {
        val currentUser = firebaseAuth.currentUser
        val userEmail = currentUser?.email ?: return Result.Error(Exception("User not logged in"))
        val userData = UserData(reason)

        return try {
            val documentRef = firestore.collection("users")
                .document(userEmail)
                .collection("outings")
                .add(userData)
                .await()
            documentId = documentRef.id
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<Boolean> =
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }


    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        roll: String,
        phone: String
    ): Result<Boolean> =
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = User(name, roll, phone, email, password)
            saveUserToFirestore(user)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    private fun saveUserToFirestore(user: User) {
        firestore.collection("users")
            .document(user.email)
            .set(user)
    }

    fun logout(): Result<Boolean> {
        return try {
            firebaseAuth.signOut()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}