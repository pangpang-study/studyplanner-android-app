package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("email")val email: String,
    @SerializedName("nick")val nick: String,
    @SerializedName("success")val success: Boolean,
    @SerializedName("code")val code: String,
    @SerializedName("error")val error: String
)
