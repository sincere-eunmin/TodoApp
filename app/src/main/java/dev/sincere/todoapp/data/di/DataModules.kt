package dev.sincere.todoapp.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sincere.todoapp.data.local.LocalDataSource
import dev.sincere.todoapp.data.local.LocalDataSourceImpl
import dev.sincere.todoapp.data.local.dao.TodoDao
import dev.sincere.todoapp.data.local.database.TodoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {
    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDatabase =
        Room.databaseBuilder(appContext, TodoDatabase::class.java, "todo_database").build()

    @Singleton
    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: TodoDao): LocalDataSource = LocalDataSourceImpl(dao)
}