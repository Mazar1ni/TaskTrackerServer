package com.github.mazar1ni.tasks.data.request

import com.github.mazar1ni.tasks.data.models.remote.DeletedTaskDto
import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SyncTasksRequest(
    @SerialName("tasks")
    val tasks: List<TaskDto>?,
    @SerialName("deleted_tasks_uuid")
    val deletedTasksUUID: List<DeletedTaskDto>?,
    @SerialName("last_update_timestamp")
    val lastUpdateTimestamp: Long?
)
