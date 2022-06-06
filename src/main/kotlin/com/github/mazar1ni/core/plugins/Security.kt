package com.github.mazar1ni.core.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.mazar1ni.core.constants.JWTConstants
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = JWTConstants.realm
            verifier(
                JWT.require(Algorithm.HMAC256(JWTConstants.secret))
                    .withAudience(JWTConstants.audience)
                    .withIssuer(JWTConstants.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(JWTConstants.audience))
                    JWTPrincipal(credential.payload)
                else null
            }
        }
    }
}

val JWTPrincipal.userId: String?
    get() = getClaim("userId", String::class)
