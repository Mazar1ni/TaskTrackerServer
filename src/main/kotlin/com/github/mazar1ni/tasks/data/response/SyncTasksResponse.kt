package com.github.mazar1ni.tasks.data.response

import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SyncTasksResponse(
    @SerialName("all_tasks_uuid")
    val uuidAllTasks: List<String>?,
    @SerialName("updated_tasks")
    val updatedTasks: List<TaskDto>?
)
