package com.github.chufarnovevgeniy.testpeopleapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}