package dev.sincere.todoapp.core.mapper

import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.data.local.model.TodoModel

fun Todo.toLocalModel(): TodoModel = TodoModel(
    id, title, content, completion
)

fun TodoModel.toEntity(): Todo = Todo(
    id, title, content, completion
)