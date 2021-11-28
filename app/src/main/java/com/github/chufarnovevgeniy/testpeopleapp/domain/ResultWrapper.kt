package com.github.chufarnovevgeniy.testpeopleapp.domain

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    object Loading : ResultWrapper<Nothing>()
    object Error : ResultWrapper<Nothing>()
}