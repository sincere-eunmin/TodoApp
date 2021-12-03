package dev.sincere.todoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.sincere.todoapp.data.local.dao.TodoDao
import dev.sincere.todoapp.data.local.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}