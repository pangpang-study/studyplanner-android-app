package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.ApiClient
import com.example.myapplication.model.TokenSave
import com.example.myapplication.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity: AppCompatActivity() {
    private val accessTokenTextView: TextView by lazy{
        findViewById(R.id.accessTokenTextView)
    }
    private val refreshTokenTextView: TextView by lazy{
        findViewById(R.id.refreshTokenTextView)
    }
    private lateinit var tokenSave: TokenSave
    private lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tokenSave = TokenSave(this)
        apiClient = ApiClient()

        apiClient.getApiService(this).getUserInfo()
            .enqueue(object : Callback<UserInfo> {
                override fun onResponse(
                    call: Call<UserInfo>?,
                    response: Response<UserInfo>?
                ) {
                    if (response!!.isSuccessful) {
                        Toast.makeText(this@UserActivity,"${response.body()!!.nick}",Toast.LENGTH_LONG)
                    }
                    else{
                        Toast.makeText(this@UserActivity,"기간이 만료되었습니다.",Toast.LENGTH_LONG)
                    }
                }

                override fun onFailure(call: Call<UserInfo>?, t: Throwable?) {
                }

            })

    }
}