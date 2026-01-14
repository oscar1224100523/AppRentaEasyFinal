package com.oarj.rentaeasy.models

data class Property(
    val id: String = "",
    val ownerId: String = "",
    val ownerName: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val location: String = "",
    val address: String = "",
    val imageUrls: List<String> = emptyList(),
    val bedrooms: Int = 0,
    val bathrooms: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)