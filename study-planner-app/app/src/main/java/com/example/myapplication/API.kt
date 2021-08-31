package com.example.myapplication

import com.example.myapplication.model.UserInfo
import com.example.myapplication.model.UserLoginResponse
import com.example.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface API {
    //TODO 나중에 인자로 토큰이 들어가서 정보 받아옴
    @GET("/api/v1/user/profile")
    fun getUserInfo(): Call<UserInfo>

    @POST("/api/v1/auth/join")
    @FormUrlEncoded
    fun joinUser(
        @FieldMap param : MutableMap<String,String?>
    ): Call<UserResponse>

    @POST("/api/v1/auth/login")
    @FormUrlEncoded
    fun loginUser(
        @FieldMap param : MutableMap<String,String?>
    ): Call<UserLoginResponse>
}