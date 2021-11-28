package com.github.chufarnovevgeniy.testpeopleapp.domain

import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

interface UsersLocalRepo {
    suspend fun getUsers(): List<UserEntity>
    suspend fun cacheUsers(users: List<UserEntity>)
    suspend fun getUserById(id: Int): UserEntity?
}