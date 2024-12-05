package com.compose.androidremind.buildInstance.mvi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.compose.androidremind.buildInstance.mvi.entity.Todo

@Dao
interface TodoDao {
    @Query("select * from todo")
    fun getAll():List<Todo>
    @Query("select * from todo where id=:id")
    fun findById(id:String):Todo
    @Insert
    fun insert(todo: Todo)
    @Update
    fun update(todo:Todo)
}