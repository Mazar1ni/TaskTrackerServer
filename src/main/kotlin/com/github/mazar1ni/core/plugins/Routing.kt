package com.github.mazar1ni.core.plugins

import com.github.mazar1ni.auth.routes.loginUser
import com.github.mazar1ni.auth.routes.register
import com.github.mazar1ni.core.service.UserService
import com.github.mazar1ni.tasks.routes.getAllTasks
import com.github.mazar1ni.tasks.routes.syncTasks
import com.github.mazar1ni.tasks.service.TasksService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject(UserService::class.java)
    val tasksService: TasksService by inject(TasksService::class.java)

    routing {
        loginUser(userService)
        register(userService)

        authenticate {
            getAllTasks(tasksService)
            syncTasks(tasksService)
        }
    }
}
