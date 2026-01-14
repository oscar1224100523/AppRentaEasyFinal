package com.oarj.rentaeasy.models

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val userType: String = "", // "inquilino" o "propietario"
    val phoneNumber: String = "",
    val profileImageUrl: String = ""
)