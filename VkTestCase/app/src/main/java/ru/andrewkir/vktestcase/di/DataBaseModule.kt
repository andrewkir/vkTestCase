package ru.andrewkir.vktestcase.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.andrewkir.data.db.TodoDataBase
import javax.inject.Singleton


@Module
class DataBaseModule(context: Context) {
    private val context: Context

    init {
        this.context = context
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): TodoDataBase {
        return Room.databaseBuilder(context, TodoDataBase::class.java, "todo_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

}