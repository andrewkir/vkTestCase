package ru.andrewkir.domain.usecases

import ru.andrewkir.domain.models.TodoModel
import ru.andrewkir.domain.repositories.MainRepository
import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val repository: MainRepository) {
    operator fun invoke(item: TodoModel) {
        return repository.addItem(item)
    }
}