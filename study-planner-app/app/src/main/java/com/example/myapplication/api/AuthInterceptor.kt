package com.example.myapplication.api

import android.content.Context
import com.example.myapplication.model.TokenSave
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context): Interceptor {

    private val tokenSave = TokenSave(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        tokenSave.fetchAccessToken()?.let{
            requestBuilder.addHeader("Authorization","Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}