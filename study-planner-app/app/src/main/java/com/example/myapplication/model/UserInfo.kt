package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("email")val email: String,
    @SerializedName("nick")val nick: String,
    @SerializedName("password")val password:String
)
