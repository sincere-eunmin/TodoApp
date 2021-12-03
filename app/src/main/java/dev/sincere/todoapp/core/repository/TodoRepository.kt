package dev.sincere.todoapp.core.repository

import dev.sincere.todoapp.core.mapper.toEntity
import dev.sincere.todoapp.core.mapper.toLocalModel
import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.data.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun saveTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}

class TodoRepositoryImpl(
    private val localDataSource: LocalDataSource
): TodoRepository {
    override fun getTodos(): Flow<List<Todo>> = localDataSource.getTodos().map { data -> data.map { it.toEntity() } }

    override suspend fun saveTodo(todo: Todo) = localDataSource.saveTodo(todo.toLocalModel())

    override suspend fun deleteTodo(todo: Todo) = localDataSource.deleteTodo(todo.toLocalModel())

}