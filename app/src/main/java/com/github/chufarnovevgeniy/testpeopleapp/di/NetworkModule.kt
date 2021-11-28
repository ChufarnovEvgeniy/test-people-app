package com.github.chufarnovevgeniy.testpeopleapp.di

import com.github.chufarnovevgeniy.testpeopleapp.BuildConfig
import com.github.chufarnovevgeniy.testpeopleapp.data.remote.UsersApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.USERS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}