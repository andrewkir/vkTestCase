package ru.andrewkir.data.entities

import androidx.room.*
import ru.andrewkir.domain.models.TodoModel

@Dao
interface TodoModelDao {
    @Query("SELECT * FROM TodoEntity")
    fun getTODOList(): List<TodoModel>


    @Insert
    fun addItem(item: TodoEntity)


    @Delete
    fun removeItem(item: TodoEntity)

    @Update
    fun updateItem(item: TodoEntity)
}