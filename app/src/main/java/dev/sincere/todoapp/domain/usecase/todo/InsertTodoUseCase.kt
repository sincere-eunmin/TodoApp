package dev.sincere.todoapp.domain.usecase.todo

import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.core.repository.TodoRepository
import dev.sincere.todoapp.domain.usecase.UseCase

abstract class InsertTodoUseCase: UseCase<Todo, Unit>() {
    suspend operator fun invoke(title: String, content: String, completion: Boolean) = execute(toParams(title, content, completion))

    private fun toParams(title: String, content: String, completion: Boolean): Todo = Todo(0L, title, content, completion)
}

class InsertTodoUseCaseImpl(
    private val repository: TodoRepository
): InsertTodoUseCase() {
    override suspend fun execute(params: Todo) = repository.saveTodo(params)
}