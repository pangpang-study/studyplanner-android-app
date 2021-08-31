package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("code") val code: String,
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("error")val error: String
)
