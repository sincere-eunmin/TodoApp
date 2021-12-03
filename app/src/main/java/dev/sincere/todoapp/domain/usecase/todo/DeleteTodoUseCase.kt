package dev.sincere.todoapp.domain.usecase.todo

import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.core.repository.TodoRepository
import dev.sincere.todoapp.domain.usecase.UseCase

abstract class DeleteTodoUseCase: UseCase<Todo, Unit>() {
    suspend operator fun invoke(id: Long, title: String, content: String, completion: Boolean) = execute(toParams(id, title, content, completion))

    private fun toParams(id: Long, title: String, content: String, completion: Boolean): Todo = Todo(id, title, content, completion)
}

class DeleteTodoUseCaseImpl(
    private val repository: TodoRepository
): DeleteTodoUseCase() {
    override suspend fun execute(params: Todo) = repository.deleteTodo(params)
}