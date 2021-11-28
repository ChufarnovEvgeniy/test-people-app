package com.github.chufarnovevgeniy.testpeopleapp.domain.entities

import android.os.Parcelable
import androidx.room.*
import com.github.chufarnovevgeniy.testpeopleapp.data.local.FriendsTypeConverter
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@TypeConverters(FriendsTypeConverter::class)
@Entity(tableName = "users")
@Parcelize
data class UserEntity(
    @ColumnInfo(name = "id") val id: Int,
    @PrimaryKey
    @ColumnInfo(name = "guid") val guid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int?,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "about") val about: String?,
    @ColumnInfo(name = "is_active") val isActive: Boolean,
    @ColumnInfo(name = "eye_color") val eyeColor: String?,
    @ColumnInfo(name = "favorite_fruit") val favoriteFruit: String?,
    @ColumnInfo(name = "registered") val registeredDate: Long?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "coordinates") val coordinates: String?,
    @ColumnInfo(name = "friends") val friends: List<Int>?
) : Parcelable {
    @IgnoredOnParcel
    @Ignore
    var isInfoHeader = false
}