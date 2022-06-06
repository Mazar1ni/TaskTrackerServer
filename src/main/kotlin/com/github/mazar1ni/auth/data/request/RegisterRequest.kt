package com.github.mazar1ni.auth.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("email")
    val email: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
