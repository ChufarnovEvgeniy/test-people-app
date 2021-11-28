package com.github.chufarnovevgeniy.testpeopleapp.domain

import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

interface UsersRepo {
    suspend fun getUsers(): ResultWrapper<List<UserEntity>>
    suspend fun getUsersFromNetwork(): ResultWrapper<List<UserEntity>>
    suspend fun getUserById(id: Int): ResultWrapper<UserEntity?>
}