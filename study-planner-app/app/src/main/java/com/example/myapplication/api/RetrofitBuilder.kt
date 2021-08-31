package com.example.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//싱글톤
object RetrofitBuilder {
    var retrofitService : API

    init{
        val retrofit= Retrofit.Builder()
            .baseUrl("http://3.35.13.100:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService =retrofit.create(API::class.java)
    }
}