package com.github.chufarnovevgeniy.testpeopleapp.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.location.Location
import com.github.chufarnovevgeniy.testpeopleapp.data.remote.entities.ResponseUserEntity
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import java.util.*

private var serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX", Locale.getDefault())

fun List<ResponseUserEntity>.mapToUserEntity(): List<UserEntity> = map {
    UserEntity(
        it.id,
        it.guid,
        it.name,
        it.age,
        it.company,
        it.email,
        it.phone,
        it.address,
        it.about,
        it.isActive,
        it.eyeColor,
        it.favoriteFruit,
        parseRegisteredDate(it.registeredDate),
        it.latitude,
        it.longitude,
        toCoordinates(it.latitude, it.longitude),
        it.friends?.map { friend -> friend.id }
    )
}

private fun toCoordinates(latitude: Double?, longitude: Double?): String? {
    return if (latitude != null && longitude != null) {
        "${
            Location.convert(latitude, Location.FORMAT_SECONDS)
        } ${
            Location.convert(longitude, Location.FORMAT_SECONDS)
        }"
    } else {
        null
    }
}

private fun parseRegisteredDate(date: String?): Long? {
    serverDateFormat.timeZone = TimeZone.GMT_ZONE

    return try {
        serverDateFormat.parse(date).time
    } catch (e: Exception) {
        null
    }
}