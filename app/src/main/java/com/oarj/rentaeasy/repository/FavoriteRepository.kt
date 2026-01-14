package com.oarj.rentaeasy.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oarj.rentaeasy.models.Favorite
import com.oarj.rentaeasy.models.Property
import kotlinx.coroutines.tasks.await

class FavoriteRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun addFavorite(userId: String, propertyId: String): Result<Unit> {
        return try {
            val favorite = Favorite(
                userId = userId,
                propertyId = propertyId
            )
            firestore.collection("favorites").add(favorite).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeFavorite(userId: String, propertyId: String): Result<Unit> {
        return try {
            val snapshot = firestore.collection("favorites")
                .whereEqualTo("userId", userId)
                .whereEqualTo("propertyId", propertyId)
                .get()
                .await()

            for (document in snapshot.documents) {
                document.reference.delete().await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFavorites(userId: String): Result<List<String>> {
        return try {
            val snapshot = firestore.collection("favorites")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val propertyIds = snapshot.documents.mapNotNull { document ->
                document.toObject(Favorite::class.java)?.propertyId
            }
            Result.success(propertyIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun isFavorite(userId: String, propertyId: String): Boolean {
        return try {
            val snapshot = firestore.collection("favorites")
                .whereEqualTo("userId", userId)
                .whereEqualTo("propertyId", propertyId)
                .get()
                .await()

            !snapshot.isEmpty
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getFavoriteProperties(userId: String): Result<List<Property>> {
        return try {
            val favoritesSnapshot = firestore.collection("favorites")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val propertyIds = favoritesSnapshot.documents.mapNotNull { document ->
                document.toObject(Favorite::class.java)?.propertyId
            }

            if (propertyIds.isEmpty()) {
                return Result.success(emptyList())
            }

            val properties = mutableListOf<Property>()
            for (propertyId in propertyIds) {
                val propertyDoc = firestore.collection("properties").document(propertyId).get().await()
                propertyDoc.toObject(Property::class.java)?.copy(id = propertyDoc.id)?.let {
                    properties.add(it)
                }
            }

            Result.success(properties)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}