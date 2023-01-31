package ru.andrewkir.vktestcase.di

import androidx.viewbinding.ViewBinding
import dagger.Component
import ru.andrewkir.vktestcase.common.BaseFragment
import ru.andrewkir.vktestcase.common.BaseViewModel
import ru.andrewkir.vktestcase.flows.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataModule::class, AppModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(baseFragment: BaseFragment<BaseViewModel, ViewBinding>)
    fun inject(mainFragment: MainFragment)
}