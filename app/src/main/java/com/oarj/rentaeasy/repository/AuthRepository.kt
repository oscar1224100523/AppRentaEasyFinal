package com.oarj.rentaeasy.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.oarj.rentaeasy.models.User
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun register(email: String, password: String, name: String, userType: String): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Error al crear usuario")

            val user = User(
                id = userId,
                name = name,
                email = email,
                userType = userType
            )

            firestore.collection("users").document(userId).set(user).await()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Error al iniciar sesión")

            val document = firestore.collection("users").document(userId).get().await()
            val user = document.toObject(User::class.java) ?: throw Exception("Usuario no encontrado")

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    suspend fun getCurrentUser(): User? {
        return try {
            val userId = getCurrentUserId() ?: return null
            val document = firestore.collection("users").document(userId).get().await()
            document.toObject(User::class.java)
        } catch (e: Exception) {
            null
        }
    }
}