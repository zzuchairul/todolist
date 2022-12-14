package com.d121201004.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo WHERE isFinished=0")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE isFinished=1")
    fun getAllHistory(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Todo)

    @Query("UPDATE todo SET isFinished=1 WHERE id=:id")
    suspend fun updateFinished(id: Int)

    @Query("DELETE FROM todo WHERE id=:id")
    suspend fun deleteTodo(id: Int)
}