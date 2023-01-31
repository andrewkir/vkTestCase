package ru.andrewkir.vktestcase.flows.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.andrewkir.vktestcase.common.BaseViewModel
import ru.andrewkir.vktestcase.flows.main.model.TodoModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel(
) {

    var mTodoData: MutableList<TodoModel> = mutableListOf()

    private val _todoData = MutableStateFlow(listOf(TodoModel()))
    val todoData: StateFlow<List<TodoModel>> = _todoData

    private fun getData() {
        viewModelScope.launch {
            mTodoData = repository.getData()
            _todoData.emit(mTodoData)
        }
    }

    fun removeItem(item: TodoModel) {
        mTodoData.remove(item)
        viewModelScope.launch {
            _todoData.emit(mTodoData)
        }
    }

    fun addItem(text: String) {
        mTodoData.add(TodoModel(text))
        viewModelScope.launch {
            val newList = mutableListOf<TodoModel>()
            newList.addAll(mTodoData)
            _todoData.emit(newList)
        }
    }

    init {
        getData()
    }
}