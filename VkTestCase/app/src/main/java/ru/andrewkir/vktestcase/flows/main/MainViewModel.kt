package ru.andrewkir.vktestcase.flows.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.andrewkir.vktestcase.common.BaseViewModel
import ru.andrewkir.domain.models.TodoModel
import ru.andrewkir.domain.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel(
) {

    var mTodoData: MutableList<TodoModel> = mutableListOf()

    private val _todoData = MutableStateFlow(listOf(TodoModel()))
    val todoData: StateFlow<List<TodoModel>> = _todoData

    private fun getData() {
        viewModelScope.launch {
            mTodoData = repository.getTODOList() as MutableList<TodoModel>
            _todoData.emit(mTodoData)
        }
    }

    fun removeItem(item: TodoModel) {
        mTodoData.remove(item)
        viewModelScope.launch {
            repository.removeItem(item)
            mTodoData = repository.getTODOList() as MutableList<TodoModel>
            _todoData.emit(mTodoData)
        }
    }

    fun addItem(text: String) {
        viewModelScope.launch {
            repository.addItem(TodoModel(text = text))
            mTodoData = repository.getTODOList() as MutableList<TodoModel>
            _todoData.emit(mTodoData)
        }
    }

    fun updateItem(item: TodoModel){
        viewModelScope.launch {
            repository.updateItem(item)
            mTodoData = repository.getTODOList() as MutableList<TodoModel>
            _todoData.emit(mTodoData)
        }
    }

    init {
        getData()
    }
}