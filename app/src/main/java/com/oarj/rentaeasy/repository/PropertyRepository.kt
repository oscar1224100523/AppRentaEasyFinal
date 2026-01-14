package com.oarj.rentaeasy.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.oarj.rentaeasy.models.Property
import kotlinx.coroutines.tasks.await
import java.util.UUID

class PropertyRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    suspend fun createProperty(property: Property, imageUris: List<Uri>): Result<String> {
        return try {
            val imageUrls = uploadImages(imageUris)
            val newProperty = property.copy(imageUrls = imageUrls)

            val documentRef = firestore.collection("properties").add(newProperty).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun uploadImages(imageUris: List<Uri>): List<String> {
        val urls = mutableListOf<String>()
        for (uri in imageUris) {
            val imageName = "property_${UUID.randomUUID()}.jpg"
            val storageRef = storage.reference.child("properties/$imageName")
            storageRef.putFile(uri).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            urls.add(downloadUrl)
        }
        return urls
    }

    suspend fun getAllProperties(): Result<List<Property>> {
        return try {
            val snapshot = firestore.collection("properties")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .await()

            val properties = snapshot.documents.mapNotNull { document ->
                document.toObject(Property::class.java)?.copy(id = document.id)
            }
            Result.success(properties)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun searchProperties(query: String): Result<List<Property>> {
        return try {
            val snapshot = firestore.collection("properties").get().await()
            val properties = snapshot.documents.mapNotNull { document ->
                document.toObject(Property::class.java)?.copy(id = document.id)
            }.filter {
                it.location.contains(query, ignoreCase = true) ||
                        it.title.contains(query, ignoreCase = true) ||
                        it.address.contains(query, ignoreCase = true)
            }
            Result.success(properties)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPropertyById(propertyId: String): Result<Property> {
        return try {
            val document = firestore.collection("properties").document(propertyId).get().await()
            val property = document.toObject(Property::class.java)?.copy(id = document.id)
                ?: throw Exception("Propiedad no encontrada")
            Result.success(property)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProperty(propertyId: String, property: Property): Result<Unit> {
        return try {
            firestore.collection("properties").document(propertyId).set(property).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteProperty(propertyId: String): Result<Unit> {
        return try {
            firestore.collection("properties").document(propertyId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPropertiesByOwner(ownerId: String): Result<List<Property>> {
        return try {
            val snapshot = firestore.collection("properties")
                .whereEqualTo("ownerId", ownerId)
                .get()
                .await()

            val properties = snapshot.documents.mapNotNull { document ->
                document.toObject(Property::class.java)?.copy(id = document.id)
            }
            Result.success(properties)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}