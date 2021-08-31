package com.example.myapplication.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private lateinit var apiService: API

    fun getApiService(context: Context): API {

        if(!::apiService.isInitialized){
            val retrofit= Retrofit.Builder()
                .baseUrl("http://3.35.13.100:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()
            apiService = retrofit.create(API::class.java)
        }
        return apiService
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()
    }
}