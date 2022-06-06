package com.github.mazar1ni.tasks.service

import com.github.mazar1ni.tasks.data.models.local.TaskDao
import com.github.mazar1ni.tasks.data.models.remote.DeletedTaskDto
import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import com.github.mazar1ni.tasks.data.repository.TasksRepository

class TasksService(private val tasksRepository: TasksRepository) {

    suspend fun getTasksByUserId(userId: String): List<TaskDao> {
        return tasksRepository.getTasksByUserId(userId)
    }

    suspend fun updateTasks(userId: String, tasks: List<TaskDto>) {
        tasksRepository.updateTasks(userId, tasks)
    }

    suspend fun deleteTasksByUUID(userId: String, tasksUUID: List<DeletedTaskDto>) {
        tasksRepository.deleteTasksByUUID(userId, tasksUUID)
    }

}
