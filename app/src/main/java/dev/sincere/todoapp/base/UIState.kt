package dev.sincere.todoapp.base

sealed class UIState<out T> {
    object Waiting: UIState<Nothing>()
    object Loading: UIState<Nothing>()
    data class Error(val throwable: Throwable): UIState<Nothing>()
    data class Success<out T>(val data: T): UIState<T>()
}