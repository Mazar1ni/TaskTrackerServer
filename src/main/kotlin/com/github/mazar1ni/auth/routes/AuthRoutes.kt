package com.github.mazar1ni.auth.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.mazar1ni.core.constants.JWTConstants
import com.github.mazar1ni.core.constants.NetworkResultConstants
import com.github.mazar1ni.auth.data.request.LoginRequest
import com.github.mazar1ni.auth.data.request.RegisterRequest
import com.github.mazar1ni.auth.data.response.LoginResponse
import com.github.mazar1ni.core.data.response.ApiResponse
import com.github.mazar1ni.core.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.loginUser(userService: UserService) {
    post("/user/login") {
        val request = call.receiveOrNull<LoginRequest>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (request.username.isBlank() || request.password.isBlank()) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = userService.getUserByUsername(request.username) ?: run {
            call.respond(
                HttpStatusCode.OK, ApiResponse<Unit>(status = NetworkResultConstants.INVALID_CREDENTIALS)
            )
            return@post
        }

        if (request.password != user.password) {
            call.respond(
                HttpStatusCode.OK, ApiResponse<Unit>(status = NetworkResultConstants.INVALID_CREDENTIALS)
            )
            return@post
        }

        val token = generateToken(user.id)

        call.respond(ApiResponse(200, LoginResponse(token, token)))
    }
}

fun Route.register(userService: UserService) {
    post("/user/register") {
        val request = call.receiveOrNull<RegisterRequest>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (request.email.isBlank() || request.username.isBlank() || request.password.isBlank()) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = userService.getUserByUsername(request.username)
        if (user != null) {
            call.respond(
                HttpStatusCode.OK, ApiResponse<Unit>(status = NetworkResultConstants.USERNAME_ALREADY_FOUND)
            )
            return@post
        }

        val registeredId = userService.createUser(request.email, request.username, request.password)

        val token = generateToken(registeredId)

        call.respond(ApiResponse(200, LoginResponse(token, token)))
    }
}

private fun generateToken(userId: String) =
    JWT.create().withAudience(JWTConstants.audience).withIssuer(JWTConstants.issuer)
        .withClaim("userId", userId).withExpiresAt(Date(System.currentTimeMillis() + (1000L * 60L * 60L * 24L * 365L)))
        .sign(Algorithm.HMAC256(JWTConstants.secret))
