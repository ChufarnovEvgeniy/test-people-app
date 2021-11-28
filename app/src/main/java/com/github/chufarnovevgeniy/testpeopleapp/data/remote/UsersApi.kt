package com.github.chufarnovevgeniy.testpeopleapp.data.remote

import com.github.chufarnovevgeniy.testpeopleapp.BuildConfig
import com.github.chufarnovevgeniy.testpeopleapp.data.remote.entities.ResponseUserEntity
import retrofit2.http.GET
import retrofit2.http.Query

private const val ALT_VALUE = "media"

interface UsersApi {
    @GET("o/users.json")
    suspend fun getUsers(
        @Query("alt") alt: String = ALT_VALUE,
        @Query("token") token: String = BuildConfig.API_TOKEN
    ): List<ResponseUserEntity>
}