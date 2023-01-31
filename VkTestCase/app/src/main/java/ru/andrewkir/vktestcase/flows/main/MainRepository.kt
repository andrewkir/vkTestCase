package ru.andrewkir.vktestcase.flows.main

import ru.andrewkir.vktestcase.common.BaseRepository
import ru.andrewkir.vktestcase.flows.main.model.TodoModel

class MainRepository : BaseRepository() {
    fun getData(): MutableList<TodoModel> = mutableListOf(
        TodoModel("Test", false),
        TodoModel("Test2", false),
        TodoModel("Купить картошку", true)
    )
}