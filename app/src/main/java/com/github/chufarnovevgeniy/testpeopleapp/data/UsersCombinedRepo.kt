package com.github.chufarnovevgeniy.testpeopleapp.data

import com.github.chufarnovevgeniy.testpeopleapp.data.remote.UsersApi
import com.github.chufarnovevgeniy.testpeopleapp.domain.ResultWrapper
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersLocalRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.utils.mapToUserEntity

class UsersCombinedRepo(
    private val usersApi: UsersApi,
    private val usersLocalRepo: UsersLocalRepo
) : UsersRepo {

    override suspend fun getUsers(): ResultWrapper<List<UserEntity>> {
        val cached = usersLocalRepo.getUsers()

        return if (cached.isNotEmpty()) {
            ResultWrapper.Success(cached)
        } else {
            getUsersFromNetwork()
        }
    }

    override suspend fun getUsersFromNetwork(): ResultWrapper<List<UserEntity>> {
        return try {
            val response = usersApi.getUsers().mapToUserEntity()
            usersLocalRepo.cacheUsers(response)
            ResultWrapper.Success(response)
        } catch (e: Exception) {
            ResultWrapper.Error
        }
    }

    override suspend fun getUserById(id: Int): ResultWrapper<UserEntity> {
        val cachedUser = usersLocalRepo.getUserById(id)

        return if (cachedUser != null) {
            ResultWrapper.Success(cachedUser)
        } else {
            ResultWrapper.Error
        }
    }
}
