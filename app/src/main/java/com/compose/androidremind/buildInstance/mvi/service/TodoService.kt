package com.compose.androidremind.buildInstance.mvi.service

import com.compose.androidremind.buildInstance.mvi.entity.Todo

class TodoService(private val dao: TodoDao) {
    fun getAll() = dao.getAll()
    fun findById(id: String) = dao.findById(id)
    fun insert(todo: Todo) = dao.insert(todo)
    fun update(todo: Todo) = dao.update(todo)
}