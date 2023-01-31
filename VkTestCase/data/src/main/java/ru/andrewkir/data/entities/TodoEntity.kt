package ru.andrewkir.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoEntity")
data class TodoEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean
)