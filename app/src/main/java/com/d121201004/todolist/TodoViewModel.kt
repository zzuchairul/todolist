package com.d121201004.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val allTodos: LiveData<List<Todo>> = repository.readAllTodos
    val allHistory: LiveData<List<Todo>> = repository.readAllHistory

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insertTodo(todo)
    }
    fun finish(todo: Todo) = viewModelScope.launch {
        repository.finishTodo(todo)
    }
    fun delete(todo: Todo) = viewModelScope.launch {
        repository.deleteTodo(todo)
    }
}

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}