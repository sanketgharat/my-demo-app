package com.sg.mydemoapp.ui.users.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.mydemoapp.data.UsersRepository
import com.sg.mydemoapp.data.local.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    var usersLocal: LiveData<User> = MutableLiveData()

    init {
        viewModelScope.launch {

        }
    }

    fun getUserData(userId: Int): LiveData<User> {
        usersLocal = repository.getUserFromLocal(userId)
        return usersLocal
    }
}