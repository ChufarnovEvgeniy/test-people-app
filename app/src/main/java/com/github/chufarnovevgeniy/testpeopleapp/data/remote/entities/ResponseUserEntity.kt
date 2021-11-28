package com.github.chufarnovevgeniy.testpeopleapp.data.remote.entities

import com.google.gson.annotations.SerializedName

data class ResponseUserEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("guid") val guid: String,
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int? = null,
    @SerializedName("company") val company: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("about") val about: String? = null,
    @SerializedName("isActive") val isActive: Boolean = false,
    @SerializedName("eyeColor") val eyeColor: String? = null,
    @SerializedName("favoriteFruit") val favoriteFruit: String? = null,
    @SerializedName("registered") val registeredDate: String? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("friends") val friends: List<FriendId>? = null
)

data class FriendId(
    @SerializedName("id") val id: Int
)