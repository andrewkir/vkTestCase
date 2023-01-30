package ru.andrewkir.vktestcase.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.andrewkir.vktestcase.flows.main.MainRepository

@Module
class DataModule {
    @Provides
    fun provideMainRepository(): MainRepository =
        MainRepository()
}