package com.github.mazar1ni.tasks.data.repository

import com.github.mazar1ni.tasks.data.models.local.TaskDao
import com.github.mazar1ni.tasks.data.models.remote.DeletedTaskDto
import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class TasksRepository(db: CoroutineDatabase) {

    private val tasks = db.getCollection<TaskDao>()

    suspend fun getTasksByUserId(userId: String): List<TaskDao> {
        return tasks.find(TaskDao::userId eq userId).toList()
    }

    suspend fun updateTasks(userId: String, updatedTasks: List<TaskDto>) {
        updatedTasks.forEach {

            tasks.findOneAndUpdate(
                and(TaskDao::userId eq userId, TaskDao::uuid eq it.uuid, TaskDao::timestamp lt it.timestamp),
                combine(
                    setValue(TaskDao::title, it.title),
                    setValue(TaskDao::description, it.description),
                    setValue(TaskDao::timestamp, it.timestamp)
                )
            )

            val task = tasks.findOne(and(TaskDao::userId eq userId, TaskDao::uuid eq it.uuid),)

            if (task == null)
                tasks.insertOne(TaskDao(userId, it.title, it.description, it.uuid, it.timestamp))
        }
    }

    suspend fun deleteTasksByUUID(userId: String, tasksUUID: List<DeletedTaskDto>) {
        tasksUUID.forEach {
            tasks.findOneAndDelete(
                and(
                    TaskDao::userId eq userId,
                    TaskDao::uuid eq it.uuid,
                    TaskDao::timestamp lt it.timestamp
                )
            )
        }
    }

}
