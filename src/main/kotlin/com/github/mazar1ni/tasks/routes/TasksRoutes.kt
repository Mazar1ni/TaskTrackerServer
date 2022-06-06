package com.github.mazar1ni.tasks.routes

import com.github.mazar1ni.core.data.response.ApiResponse
import com.github.mazar1ni.tasks.data.models.remote.TaskDto
import com.github.mazar1ni.tasks.data.request.SyncTasksRequest
import com.github.mazar1ni.tasks.data.response.AllTasksResponse
import com.github.mazar1ni.tasks.data.response.SyncTasksResponse
import com.github.mazar1ni.tasks.service.TasksService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllTasks(tasksService: TasksService) {
    get("/tasks/getAll") {

        val principal = call.principal<JWTPrincipal>()
        principal?.let {
            val userId = it.payload.getClaim("userId").asString()

            val tasks = tasksService.getTasksByUserId(userId)

            call.respond(
                ApiResponse(
                    200, AllTasksResponse(
                        tasks.map { task ->
                            TaskDto(task.title, task.description, task.uuid, task.timestamp)
                        })
                )
            )
            return@get
        }

        call.respond(HttpStatusCode.InternalServerError)
    }
}

fun Route.syncTasks(tasksService: TasksService) {
    post("/tasks/syncTasks") {

        val request = call.receiveOrNull<SyncTasksRequest>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val principal = call.principal<JWTPrincipal>()
        principal?.let {
            val userId = it.payload.getClaim("userId").asString()

            request.tasks?.let {
                tasksService.updateTasks(userId, request.tasks)
            }

            request.deletedTasksUUID?.let {
                tasksService.deleteTasksByUUID(userId, request.deletedTasksUUID)
            }

            val tasks = tasksService.getTasksByUserId(userId)

            call.respond(
                ApiResponse(200, SyncTasksResponse(tasks.map { task -> task.uuid }))
            )
            return@post
        }

        call.respond(HttpStatusCode.InternalServerError)
    }
}