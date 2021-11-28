package com.github.chufarnovevgeniy.testpeopleapp.di

import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import com.github.chufarnovevgeniy.testpeopleapp.ui.user.UserViewModel
import com.github.chufarnovevgeniy.testpeopleapp.ui.userslist.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersListViewModel(get()) }
    viewModel { (user: UserEntity) -> UserViewModel(user, get()) }
}