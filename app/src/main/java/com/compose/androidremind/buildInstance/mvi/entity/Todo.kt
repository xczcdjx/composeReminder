package com.compose.androidremind.buildInstance.mvi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey var id: String,
    val name: String,
    var time: String = getCurrentTime(),
    var f: Boolean = false
)
fun getCurrentTime(): String {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
}
