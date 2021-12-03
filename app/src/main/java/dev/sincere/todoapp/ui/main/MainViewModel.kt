package dev.sincere.todoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sincere.todoapp.base.UIState
import dev.sincere.todoapp.core.entity.Todo
import dev.sincere.todoapp.domain.usecase.todo.GetTodosUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
): ViewModel() {
    private val _todos: MutableStateFlow<UIState<List<Todo>>> = MutableStateFlow(UIState.Waiting)
    val todos = _todos.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    init {
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            getTodosUseCase().onStart {
                _todos.value = UIState.Loading
            }.catch { exception ->
                _todos.value = UIState.Error(exception)
            }.collect { data ->
                _todos.value = UIState.Success(data)
            }
        }
    }

    fun onAddBtnClicked() {
        viewModelScope.launch {
            _event.emit(Event.AddBtnClicked)
        }
    }

    fun onDetailBtnClicked(todo: Todo) {
        viewModelScope.launch {
            _event.emit(Event.DetailBtnClicked(todo))
        }
    }

    sealed class Event {
        object AddBtnClicked: Event()
        data class DetailBtnClicked(val todo: Todo): Event()
    }
}