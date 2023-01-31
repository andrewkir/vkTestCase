package ru.andrewkir.data.repositories

import ru.andrewkir.data.entities.TodoEntity
import ru.andrewkir.data.entities.TodoModelDao
import ru.andrewkir.domain.models.TodoModel
import ru.andrewkir.domain.repositories.MainRepository
import java.util.UUID
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val todoModelDao: TodoModelDao) :
    MainRepository {
    override fun getTODOList(): List<TodoModel> {
        return todoModelDao.getTODOList()
    }

    override fun addItem(item: TodoModel) {
        todoModelDao.addItem(
            TodoEntity(
                UUID.randomUUID().toString(),
                item.text ?: "",
                item.isCompleted ?: false
            )
        )
    }

    override fun removeItem(item: TodoModel) {
        todoModelDao.removeItem(
            TodoEntity(
                item.id,
                item.text ?: "",
                item.isCompleted ?: false
            )
        )
    }

    override fun updateItem(item: TodoModel) {
        todoModelDao.updateItem(
            TodoEntity(
                item.id,
                item.text ?: "",
                item.isCompleted ?: false
            )
        )
    }
}