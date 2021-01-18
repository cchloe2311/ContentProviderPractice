package com.example.contentproviderpractice

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers

class MyViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val source =
                MyContactsDataSource(application.contentResolver)
            MainViewModel(application, MyContactsRepository(source, Dispatchers.IO)) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}