package com.d121201004.todolist

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    val readAllTodos: LiveData<List<Todo>> = todoDao.getAllTodos()
    val readAllHistory: LiveData<List<Todo>> = todoDao.getAllHistory()

    suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todo)
    }
    suspend fun finishTodo(todo: Todo) {
        todoDao.updateFinished(todo.id)
    }
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo.id)
    }
}