package com.github.mazar1ni.tasks.data.response

import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import com.google.gson.annotations.SerializedName

data class SyncTasksResponse(
    @SerializedName("all_tasks_uuid")
    val uuidAllTasks: List<String>?,
    @SerializedName("updated_tasks")
    val updatedTasks: List<TaskDto>?
)
