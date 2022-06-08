package com.github.mazar1ni.tasks.data.request

import com.github.mazar1ni.tasks.data.models.remote.DeletedTaskDto
import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import com.google.gson.annotations.SerializedName

data class SyncTasksRequest(
    @SerializedName("tasks")
    val tasks: List<TaskDto>?,
    @SerializedName("deleted_tasks_uuid")
    val deletedTasksUUID: List<DeletedTaskDto>?,
    @SerializedName("last_update_timestamp")
    val lastUpdateTimestamp: Long?
)
