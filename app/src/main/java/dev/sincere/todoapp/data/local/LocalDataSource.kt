package dev.sincere.todoapp.data.local

import dev.sincere.todoapp.data.local.dao.TodoDao
import dev.sincere.todoapp.data.local.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getTodos(): Flow<List<TodoModel>>
    suspend fun saveTodo(todo: TodoModel)
    suspend fun deleteTodo(todo: TodoModel)
}

class LocalDataSourceImpl(
    private val dao: TodoDao
): LocalDataSource {
    override fun getTodos(): Flow<List<TodoModel>> = dao.getTodos()

    override suspend fun saveTodo(todo: TodoModel) =
        if (todo.id == 0L) dao.insertTodo(todo) else dao.updateTodo(todo)

    override suspend fun deleteTodo(todo: TodoModel) = dao.deleteTodo(todo)

}