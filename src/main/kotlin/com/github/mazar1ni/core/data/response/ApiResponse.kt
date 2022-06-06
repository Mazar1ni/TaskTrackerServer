package com.github.mazar1ni.core.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("data")
    val data: T? = null
)
