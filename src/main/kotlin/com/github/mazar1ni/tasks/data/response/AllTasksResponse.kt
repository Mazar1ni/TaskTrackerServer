package com.github.mazar1ni.tasks.data.response

import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import com.google.gson.annotations.SerializedName

data class AllTasksResponse(
    @SerializedName("tasks")
    val tasks: List<TaskDto>
)
