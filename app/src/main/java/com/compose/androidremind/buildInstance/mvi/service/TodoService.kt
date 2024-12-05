package com.compose.androidremind.buildInstance.mvi.service

import com.compose.androidremind.buildInstance.mvi.entity.Todo

class TodoService(private val dao: TodoDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun findById(id: String) = dao.findById(id)
    suspend fun insert(todo: Todo) = dao.insert(todo)
    suspend fun update(todo: Todo) = dao.update(todo)
}