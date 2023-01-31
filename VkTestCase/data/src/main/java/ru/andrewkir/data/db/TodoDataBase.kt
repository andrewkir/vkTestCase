package ru.andrewkir.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.andrewkir.data.entities.TodoEntity
import ru.andrewkir.data.entities.TodoModelDao

@Database(
    entities = [TodoEntity::class],
    version = 1
)

abstract class TodoDataBase : RoomDatabase() {

    abstract fun getTODODao(): TodoModelDao
}