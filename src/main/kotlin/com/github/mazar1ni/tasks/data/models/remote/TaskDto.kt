package com.github.mazar1ni.tasks.data.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("uuid")
    val uuid: String,
    @SerialName("timestamp")
    val timestamp: Long
)
