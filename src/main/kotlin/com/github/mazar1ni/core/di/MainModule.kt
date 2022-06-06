package com.github.mazar1ni.core.di

import com.github.mazar1ni.core.constants.Constants
import com.github.mazar1ni.core.data.repository.UserRepository
import com.github.mazar1ni.core.service.UserService
import com.github.mazar1ni.tasks.data.repository.TasksRepository
import com.github.mazar1ni.tasks.service.TasksService
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }

    single<UserRepository> {
        UserRepository(get())
    }
    single<TasksRepository> {
        TasksRepository(get())
    }

    single { UserService(get()) }
    single { TasksService(get()) }
}
