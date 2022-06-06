package com.github.mazar1ni.core.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val email: String,
    val username: String,
    val password: String,
    @BsonId
    val id: String = ObjectId().toString()
)
