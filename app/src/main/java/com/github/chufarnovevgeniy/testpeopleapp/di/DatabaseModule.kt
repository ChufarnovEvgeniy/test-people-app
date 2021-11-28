package com.github.chufarnovevgeniy.testpeopleapp.di

import androidx.room.Room
import com.github.chufarnovevgeniy.testpeopleapp.data.local.UserDatabase
import org.koin.dsl.module

private const val USERS_DB_PATH = "users.db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            USERS_DB_PATH
        )
            .build()
            .usersDao()
    }
}