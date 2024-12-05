package com.compose.androidremind.buildInstance.mvi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(@PrimaryKey val id: String, val name: String, var f: Boolean = false)
