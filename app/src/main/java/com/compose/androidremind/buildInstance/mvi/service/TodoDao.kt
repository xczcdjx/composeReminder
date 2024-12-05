package com.compose.androidremind.buildInstance.mvi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.compose.androidremind.buildInstance.mvi.entity.Todo

@Dao
interface TodoDao {
    @Query("select * from todo")
    suspend fun getAll():List<Todo>
    @Query("select * from todo where id=:id")
    suspend fun findById(id:String):Todo
    @Insert
    suspend fun insert(todo: Todo)
    @Update
    suspend fun update(todo:Todo)
}