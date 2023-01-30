package ru.andrewkir.vktestcase.di

import androidx.viewbinding.ViewBinding
import dagger.Component
import ru.andrewkir.vktestcase.common.BaseFragment
import ru.andrewkir.vktestcase.common.BaseRepository
import ru.andrewkir.vktestcase.common.BaseViewModel
//import ru.andrewkir.vktestcase.flows.main.MainFragment

@Component(modules = [ViewModelModule::class, DataModule::class, AppModule::class])
interface AppComponent {
    fun inject(baseFragment: BaseFragment<BaseViewModel, BaseRepository, ViewBinding>)
//    fun inject(mainFragment: MainFragment)
}