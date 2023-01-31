package ru.andrewkir.domain.models

import java.util.UUID

data class TodoModel(
    val id: String = UUID.randomUUID().toString(),
    val text: String? = null,
    val isCompleted: Boolean? = false
)