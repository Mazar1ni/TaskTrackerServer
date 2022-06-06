package com.github.mazar1ni.core.data.repository

import com.github.mazar1ni.core.data.models.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepository(db: CoroutineDatabase) {

    private val users = db.getCollection<User>()

    suspend fun getUserByUsername(username: String): User? {
        return users.findOne(User::username eq username)
    }

    suspend fun createUser(email: String, username: String, password: String): String {
        val user = User(email, username, password)
        users.insertOne(user)
        return user.id
    }
}
