package ru.andrewkir.vktestcase.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.andrewkir.vktestcase.common.BaseRepository

abstract class BaseViewModel : ViewModel() {

    protected val mutableLoading: MutableLiveData<Boolean> = MutableLiveData()

    val loading: LiveData<Boolean>
        get() = mutableLoading
}