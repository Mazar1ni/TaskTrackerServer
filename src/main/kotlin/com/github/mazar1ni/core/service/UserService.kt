package com.github.mazar1ni.core.service

import com.github.mazar1ni.core.data.models.User
import com.github.mazar1ni.core.data.repository.UserRepository
import org.bson.BsonValue

class UserService(private val userRepository: UserRepository) {

    suspend fun getUserByUsername(username: String): User? {
        return userRepository.getUserByUsername(username)
    }

    suspend fun createUser(email: String, username: String, password: String): String {
        return userRepository.createUser(email, username, password)
    }

}
