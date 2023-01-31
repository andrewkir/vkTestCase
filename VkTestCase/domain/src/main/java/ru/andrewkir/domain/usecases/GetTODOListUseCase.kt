package ru.andrewkir.domain.usecases

import ru.andrewkir.domain.models.TodoModel
import ru.andrewkir.domain.repositories.MainRepository
import javax.inject.Inject

class GetTODOListUseCase @Inject constructor(private val repository: MainRepository) {
    operator fun invoke(): List<TodoModel> {
        return repository.getTODOList()
    }
}