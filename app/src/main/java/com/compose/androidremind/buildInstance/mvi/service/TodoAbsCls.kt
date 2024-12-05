package com.compose.androidremind.buildInstance.mvi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.compose.androidremind.buildInstance.mvi.entity.Todo

@Database([Todo::class], version = 1)
abstract class TodoAbsCls : RoomDatabase() {
    abstract fun dao(): TodoDao

    companion object {
        private var INSTANCE: TodoAbsCls? = null
        fun getInstance(ctx: Context): TodoAbsCls {
            synchronized(this){ // 避免数据库造成死锁
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(ctx, TodoAbsCls::class.java, "todo_db").build()
                }
                return instance
            }
        }
    }
}