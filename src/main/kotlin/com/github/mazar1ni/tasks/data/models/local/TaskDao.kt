package com.github.mazar1ni.tasks.data.models.local

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class TaskDao(
    val userId: String,
    val title: String,
    val description: String,
    val uuid: String,
    val timestamp: Long,
    val isCompleted: Boolean,
    @BsonId
    val id: String = ObjectId().toString()
)
