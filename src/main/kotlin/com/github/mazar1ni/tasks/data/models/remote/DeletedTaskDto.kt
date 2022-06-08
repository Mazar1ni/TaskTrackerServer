package com.github.mazar1ni.tasks.data.models.remote

import com.google.gson.annotations.SerializedName

data class DeletedTaskDto(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("timestamp")
    val timestamp: Long
)
