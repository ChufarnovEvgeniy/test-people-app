package com.github.chufarnovevgeniy.testpeopleapp.data.local

import androidx.room.TypeConverter

private const val SEPARATOR = " ,"

class FriendsTypeConverter {
    @TypeConverter
    fun fromFriends(friends: List<Int>?): String? {
        return friends?.joinToString(SEPARATOR)
    }

    @TypeConverter
    fun toFriends(data: String?): List<Int>? {
        return data?.split(SEPARATOR)?.map { it.toInt() }?.toMutableList()
    }
}