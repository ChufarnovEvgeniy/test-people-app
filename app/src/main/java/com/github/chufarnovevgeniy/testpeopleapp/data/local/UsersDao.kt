package com.github.chufarnovevgeniy.testpeopleapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

@Dao
interface UsersDao {
    @Insert
    fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): UserEntity?

    @Query("DELETE FROM users")
    fun clear()
}