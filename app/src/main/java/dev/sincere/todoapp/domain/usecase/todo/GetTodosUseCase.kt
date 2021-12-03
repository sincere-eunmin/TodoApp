package dev.sincere.todoapp.domain.usecase.todo

import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.core.repository.TodoRepository
import dev.sincere.todoapp.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

abstract class GetTodosUseCase: UseCase<Unit, Flow<List<Todo>>>() {
    suspend operator fun invoke(): Flow<List<Todo>> = execute(Unit)
}

class GetTodosUseCaseImpl(
    private val repository: TodoRepository
): GetTodosUseCase() {
    override suspend fun execute(params: Unit): Flow<List<Todo>> = repository.getTodos()
}