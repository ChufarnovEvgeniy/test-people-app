package com.github.chufarnovevgeniy.testpeopleapp.utils

import android.icu.text.SimpleDateFormat
import com.github.chufarnovevgeniy.testpeopleapp.R
import com.github.chufarnovevgeniy.testpeopleapp.domain.entities.UserEntity
import java.util.*

private var dateFormat = SimpleDateFormat("HH:mm dd.MM.yy", Locale.getDefault())

fun UserEntity.getFormattedRegisterDateOrNull() = try {
    dateFormat.format(registeredDate)
} catch (e: Exception) {
    null
}

fun UserEntity.getEyeColorId() = when (eyeColor) {
    "brown" -> {
        R.color.eye_color_brown
    }
    "green" -> {
        R.color.eye_color_green
    }
    "blue" -> {
        R.color.eye_color_blue
    }
    else -> {
        R.color.eye_color_default
    }
}

fun UserEntity.getFavoriteFruitDrawableId() = when (favoriteFruit) {
    "apple" -> {
        R.drawable.apple
    }
    "banana" -> {
        R.drawable.banana
    }
    "strawberry" -> {
        R.drawable.strawberry
    }
    else -> {
        R.drawable.ic_unknown
    }
}