package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        /*
        val editId: EditText = findViewById(R.id.editId)
        val editPassword: EditText = findViewById(R.id.editPassword)
        val loginButton: Button = findViewById(R.id.loginButton)
        var id = editId.text.toString()
        var password = editPassword.text.toString()

        var data: MutableMap<String, String> = mutableMapOf()
        data["email"]=id
        data["nick"]=id
        data["password"]=password
        loginButton.setOnClickListener {
            RetrofitBuilder.retrofitService.joinUser(data)
                .enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>?,
                        response: Response<UserResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            when (response!!.code()) {
                                201 -> Toast.makeText(this@JoinActivity, "성공", Toast.LENGTH_LONG)
                                    .show()
                                400 -> Toast.makeText(
                                    this@JoinActivity,
                                    "이미 로그인 됨",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                422 -> Toast.makeText(this@JoinActivity, "비번 틀림", Toast.LENGTH_LONG)
                                    .show()
                                else -> Toast.makeText(
                                    this@JoinActivity,
                                    "이외의오류",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else Toast.makeText(
                            this@JoinActivity,
                            "아예 실패 ${response.errorBody().toString()}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                    }

                })
        }
         */
    }
}