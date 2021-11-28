package com.github.chufarnovevgeniy.testpeopleapp.di

import com.github.chufarnovevgeniy.testpeopleapp.data.UsersCombinedRepo
import com.github.chufarnovevgeniy.testpeopleapp.data.UsersLocalRepoImpl
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersLocalRepo
import com.github.chufarnovevgeniy.testpeopleapp.domain.UsersRepo
import org.koin.dsl.module

val repoModule = module {
    single<UsersLocalRepo> {
        UsersLocalRepoImpl(get())
    }

    single<UsersRepo> {
        UsersCombinedRepo(get(), get())
    }
}