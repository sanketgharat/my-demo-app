package com.sg.mydemoapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.mydemoapp.data.UsersRepository
import com.sg.mydemoapp.data.local.database.UsersDatabase
import com.sg.mydemoapp.data.local.entity.User
import com.sg.mydemoapp.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    companion object {
        val TAG = "UsersViewModelT"
    }

    private val _users: MutableLiveData<List<User>> = MutableLiveData(listOf<User>())
    val users: LiveData<List<User>> = _users

    var usersLocal: LiveData<List<User>> = MutableLiveData(listOf<User>())
    //var usersLocal: LiveData<List<User>> = repository.getUsersFromLocal()

    private var _error = MutableLiveData("")
    val error: LiveData<String> = _error

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(true)
    val loader: LiveData<Boolean> = _loader

    init {
        viewModelScope.launch {
            usersLocal = repository.getUsersFromLocal()
        }
    }

    fun getUsers() {
        _loader.value = true
        viewModelScope.launch {
            val response = repository.getUsers()
            response?.let { responseUserList ->
                Logger.d(TAG, "api response")
                _loader.value = false
                if (responseUserList.isSuccessful && response.body() != null) {
                    Logger.d(TAG, "api response success")
                    //_users.value = response.body()
                    //insert in database
                    response.body()?.let {
                        repository.insertUsersToLocal(it)
                    }

                } else {
                    Logger.d(TAG, "api response error ${response.errorBody().toString()}")
                    _error.value = response.errorBody().toString()
                }
            }
        }
    }
}