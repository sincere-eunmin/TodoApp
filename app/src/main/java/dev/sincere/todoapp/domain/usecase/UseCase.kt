package dev.sincere.todoapp.domain.usecase

abstract class UseCase<Request, Response> {
    protected abstract suspend fun execute(params: Request): Response

    suspend operator fun <Response> UseCase<Unit, Response>.invoke(): Response = execute(Unit)
}

