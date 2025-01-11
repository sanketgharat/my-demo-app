package com.sg.mydemoapp.ui.users.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserPhotosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is photos Fragment"
    }
    val text: LiveData<String> = _text
}