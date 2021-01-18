package com.example.contentproviderpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class MainViewModel(
    context: Application,
    private val myContactsRepository: MyContactsRepository
) : AndroidViewModel(context) {

    // The coroutine block will start running after an observer is attached to the LiveData
    var myContacts: LiveData<List<MyContact>> = liveData {
        emit(myContactsRepository.fetchContacts())
    }
}