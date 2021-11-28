package com.github.chufarnovevgeniy.testpeopleapp.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.chufarnovevgeniy.testpeopleapp.domain.ResultWrapper
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import kotlinx.coroutines.launch

class UserViewModel(
    private val user: UserEntity,
    private val usersRepo: UsersRepo
) : ViewModel() {
    private val _friends = MutableLiveData<List<UserEntity>>()
    val friends: LiveData<List<UserEntity>> = _friends

    private val _userClickedEvent = MutableLiveData<UserEntity?>()
    val userClickedEvent: LiveData<UserEntity?> = _userClickedEvent

    private val _phoneClickedEvent = MutableLiveData<String?>()
    val phoneClickedEvent: LiveData<String?> = _phoneClickedEvent

    private val _emailClickedEvent = MutableLiveData<String?>()
    val emailClickedEvent: LiveData<String?> = _emailClickedEvent

    private val _coordinatesClickedEvent = MutableLiveData<String?>()
    val coordinatesClickedEvent: LiveData<String?> = _coordinatesClickedEvent

    init {
        val friendsList = mutableListOf<UserEntity>()

        friendsList.add(user.copy().apply {
            isInfoHeader = true
        })

        _friends.value = friendsList.toList()

        viewModelScope.launch {
            user.friends?.forEach { id ->
                val friendResult = usersRepo.getUserById(id)

                if (friendResult is ResultWrapper.Success) {
                    friendsList.add(friendResult.value!!)
                }
            }

            _friends.value = friendsList.toList()
        }
    }

    fun onUserClicked(user: UserEntity) {
        _userClickedEvent.value = user
    }

    fun onUserClickedEventFinished() {
        _userClickedEvent.value = null
    }

    fun onPhoneClicked() {
        user.phone?.let {
            _phoneClickedEvent.value = it
        }
    }

    fun onPhoneClickedEventFinished() {
        _phoneClickedEvent.value = null
    }

    fun onEmailClicked() {
        user.email?.let {
            _emailClickedEvent.value = it
        }
    }

    fun onEmailClickedEventFinished() {
        _emailClickedEvent.value = null
    }

    fun onCoordinatesClicked() {
        if (user.latitude != null && user.longitude != null) {
            _coordinatesClickedEvent.value = "${user.latitude},${user.longitude}"
        }
    }

    fun onCoordinatesClickedEventFinished() {
        _coordinatesClickedEvent.value = null
    }
}