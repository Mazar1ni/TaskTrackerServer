package com.github.mazar1ni.tasks.data.response

import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTasksResponse(
    @SerialName("tasks")
    val tasks: List<TaskDto>
)
