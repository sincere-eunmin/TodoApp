package dev.sincere.todoapp.data.local.dao

import androidx.room.*
import dev.sincere.todoapp.data.local.model.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("select * from todo")
    fun getTodos(): Flow<List<TodoModel>>

    @Insert
    suspend fun insertTodo(todo: TodoModel)

    @Update
    suspend fun updateTodo(todo: TodoModel)

    @Delete
    suspend fun deleteTodo(todo: TodoModel)
}