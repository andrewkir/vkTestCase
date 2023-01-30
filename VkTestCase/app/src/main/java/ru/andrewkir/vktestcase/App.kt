package ru.andrewkir.vktestcase

import android.app.Application
import ru.andrewkir.vktestcase.di.AppComponent
import ru.andrewkir.vktestcase.di.AppModule
import ru.andrewkir.vktestcase.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}