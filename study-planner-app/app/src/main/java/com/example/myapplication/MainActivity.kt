package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val editId: EditText by lazy {
        findViewById(R.id.editId)
    }
    private val editPassword: EditText by lazy {
        findViewById(R.id.editPassword)
    }
    private val loginButton: Button by lazy {
        findViewById(R.id.loginButton)
    }
    private val signButton: Button by lazy {
        findViewById(R.id.signButton)
    }
    private var id: String? = null
    private var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        signButton.setOnClickListener {
        }
        loginButton.setOnClickListener {
            id=editId.text.toString()
            password=editPassword.text.toString()

            var data: MutableMap<String, String?> = mutableMapOf()
            data.put("email", id)
            data.put("nick", id)
            data.put("password", password)
            RetrofitBuilder.retrofitService.joinUser(data)
                .enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>?,
                        response: Response<UserResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            when (response!!.code()) {
                                201 -> {
                                    Toast.makeText(this@MainActivity, "성공", Toast.LENGTH_LONG)
                                        .show()
                                    Log.d("MainActivity", "${response.body().toString()}")
                                }
                                400 -> Toast.makeText(
                                    this@MainActivity,
                                    "이미 로그인 됨",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                422 -> Toast.makeText(this@MainActivity, "비번 틀림", Toast.LENGTH_LONG)
                                    .show()
                                else -> Toast.makeText(
                                    this@MainActivity,
                                    "이외의오류",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else Toast.makeText(
                            this@MainActivity,
                            "아예 실패 ${
                                response.errorBody().toString()
                            } + ${response.code()} + ${data["email"]} + ${data["nick"]} +${data["password"]}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                    }

                })
        }
    }
}
