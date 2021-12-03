package dev.sincere.todoapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sincere.todoapp.core.repository.TodoRepository
import dev.sincere.todoapp.core.repository.TodoRepositoryImpl
import dev.sincere.todoapp.data.local.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModules {
    @Singleton
    @Provides
    fun provideTodoRepository(localDataSource: LocalDataSource): TodoRepository = TodoRepositoryImpl(localDataSource)
}