package com.oarj.rentaeasy.models

data class Favorite(
    val id: String = "",
    val userId: String = "",
    val propertyId: String = "",
    val timestamp: Long = System.currentTimeMillis()
)