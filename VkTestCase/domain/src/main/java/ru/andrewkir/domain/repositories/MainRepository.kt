package ru.andrewkir.domain.repositories

import ru.andrewkir.domain.models.TodoModel

interface MainRepository {
    fun getTODOList(): List<TodoModel>
    fun addItem(item: TodoModel)
    fun removeItem(item: TodoModel)
    fun updateItem(item: TodoModel)
}