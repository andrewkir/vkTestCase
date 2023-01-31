package ru.andrewkir.vktestcase

import android.app.Application
import androidx.room.Room
import ru.andrewkir.data.db.TodoDataBase
import ru.andrewkir.vktestcase.di.AppComponent
import ru.andrewkir.vktestcase.di.AppModule
import ru.andrewkir.vktestcase.di.DaggerAppComponent
import ru.andrewkir.vktestcase.di.DataBaseModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .dataBaseModule(DataBaseModule(context = this))
            .appModule(AppModule(context = this))
            .build()
    }
}