package com.github.chufarnovevgeniy.testpeopleapp.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.chufarnovevgeniy.testpeopleapp.domain.ResultWrapper
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val usersRepo: UsersRepo
) : ViewModel() {
    private val _state = MutableLiveData<ResultWrapper<List<UserEntity>>>()
    val state: LiveData<ResultWrapper<List<UserEntity>>> = _state

    private val _userClickedEvent = MutableLiveData<UserEntity?>()
    val userClickedEvent: LiveData<UserEntity?> = _userClickedEvent

    init {
        loadUsers()
    }

    fun onRefreshClicked() {
        _state.value = ResultWrapper.Loading

        viewModelScope.launch {
            _state.value = usersRepo.getUsersFromNetwork()
        }
    }

    private fun loadUsers() {
        _state.value = ResultWrapper.Loading

        viewModelScope.launch {
            _state.value = usersRepo.getUsers()
        }
    }

    fun onUserClicked(user: UserEntity) {
        _userClickedEvent.value = user
    }

    fun onUserEventFinished() {
        _userClickedEvent.value = null
    }
}