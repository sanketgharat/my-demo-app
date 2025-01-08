package com.sg.mydemoapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.mydemoapp.data.UsersRepository
import com.sg.mydemoapp.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData(listOf<User>())
    val users: LiveData<List<User>> = _users

    private var _error = MutableLiveData("")
    val error: LiveData<String> = _error

    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            response?.let {
                if (it.isSuccessful && response.body() != null) {
                    _users.value = response.body()
                } else {
                    _error.value = response.errorBody().toString()
                }
            }
        }
    }
}