package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("success")val success: Boolean,
    @SerializedName("code")val code: String,
    @SerializedName("error")val error: String
)
