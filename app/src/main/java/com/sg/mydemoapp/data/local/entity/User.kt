package com.sg.mydemoapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sg.mydemoapp.utils.Constants

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey
    @SerializedName("id") var id: Int? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String? = null,

    @ColumnInfo(name = "username")
    @SerializedName("username") var username: String? = null,

    @ColumnInfo(name = "email")
    @SerializedName("email") var email: String? = null,

    @ColumnInfo(name = "phone")
    @SerializedName("phone") var phone: String? = null,

    @ColumnInfo(name = "website")
    @SerializedName("website") var website: String? = null,

    ) {
    fun getPhotoUrl(): String {
        id?.let {
            return Constants.PHOTO_BASE_URL + "id/${it + 50}/" + Constants.PROFILE_PHOTO_SIZE
        }
        return ""
    }
}