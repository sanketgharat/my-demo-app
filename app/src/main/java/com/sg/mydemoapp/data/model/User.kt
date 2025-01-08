package com.sg.mydemoapp.data.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("website") var website: String? = null,

    )