package com.github.chufarnovevgeniy.testpeopleapp.data

import com.github.chufarnovevgeniy.testpeopleapp.data.local.UsersDao
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersLocalRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersLocalRepoImpl(
    private val usersDao: UsersDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersLocalRepo {

    override suspend fun getUsers(): List<UserEntity> = withContext(dispatcher) {
        usersDao.getUsers()
    }

    override suspend fun cacheUsers(users: List<UserEntity>) = withContext(dispatcher) {
        usersDao.clear()
        usersDao.insertUsers(users)
    }


    override suspend fun getUserById(id: Int): UserEntity? = withContext(dispatcher) {
        usersDao.getUser(id)
    }
}