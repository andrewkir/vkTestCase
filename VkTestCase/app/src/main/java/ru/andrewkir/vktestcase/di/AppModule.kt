package ru.andrewkir.vktestcase.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.andrewkir.data.db.TodoDataBase
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideYourDao(db: TodoDataBase) = db.getTODODao()
}