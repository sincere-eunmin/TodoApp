package dev.sincere.todoapp.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.sincere.todoapp.core.repository.TodoRepository
import dev.sincere.todoapp.domain.usecase.todo.*

@Module
@InstallIn(ViewModelComponent::class)
object DomainModules {

    @ViewModelScoped
    @Provides
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase = GetTodosUseCaseImpl(repository)

    @ViewModelScoped
    @Provides
    fun provideInsertTodoUseCase(repository: TodoRepository): InsertTodoUseCase = InsertTodoUseCaseImpl(repository)

    @ViewModelScoped
    @Provides
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase = UpdateTodoUseCaseImpl(repository)

    @ViewModelScoped
    @Provides
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase = DeleteTodoUseCaseImpl(repository)
}